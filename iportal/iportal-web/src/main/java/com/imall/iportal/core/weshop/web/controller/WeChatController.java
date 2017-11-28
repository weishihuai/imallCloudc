package com.imall.iportal.core.weshop.web.controller;

import com.imall.commons.base.util.ImageUtil;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.QRCodeTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Controller
@RequestMapping("/backendwechat")
public class WeChatController extends BaseRestSpringController {

    private static Logger log = LoggerFactory.getLogger(WeChatController.class);

    @RequestMapping(value = "/qrCodeTicket", method = RequestMethod.GET)
    @ResponseBody
    public Object qrCodeTicket(ModelMap model, @CurrUser CurrUserVo currUserVo) throws IOException {
        ResultVo resultVo = getSuccess();
        resultVo.setMsg(ServiceManager.weChatApiService.getShopPromoteQRCodeTicket(QRCodeTypeCodeEnum.QR_LIMIT_STR_SCENE, String.valueOf(currUserVo.getShopId()), null));
        return resultVo;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public Object download(String url, Integer length, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileNm = "二维码推广_"+ length +"X" + length +".png";
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileNm, "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL reqUrl = new URL(url);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) reqUrl.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(1000);
            httpUrlConn.setReadTimeout(1000);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            InputStream is = httpUrlConn.getInputStream();
            byte[] b = new byte[256];
            int i = 0;
            File tmpFile = File.createTempFile("picttemp" + System.currentTimeMillis(), "二维码推广.png");
            FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
            while ((i = is.read(b)) != -1) {
                fileOutputStream.write(b, 0, i);
            }
            File resultFile = File.createTempFile("" + System.currentTimeMillis(), fileNm);
            ImageUtil.doChangeSize(tmpFile, resultFile, length, length, true);
            FileInputStream fileInputStream = new FileInputStream(resultFile);
            b = new byte[256];
            i = 0;
            while ((i = fileInputStream.read(b)) != -1) {
                os.write(b, 0, i);
            }
            os.close();
            fileInputStream.close();
            fileOutputStream.close();
            is.close();
            httpUrlConn.disconnect();
            tmpFile.delete();
            resultFile.delete();
            return getSuccess();
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:", e);
        }
        return getSuccess();
    }
}

/**
 * 证书信任管理器（用于https请求）
 */
class MyX509TrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

