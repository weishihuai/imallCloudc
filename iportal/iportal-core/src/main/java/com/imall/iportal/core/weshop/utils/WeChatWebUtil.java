package com.imall.iportal.core.weshop.utils;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeChatWebUtil {

    private static Logger log = LoggerFactory.getLogger(WeChatWebUtil.class);

    public static String doGet(String url, String charset, int connectTimeout, int readTimeout) {
        return httpRequest(url, "GET", null, charset, connectTimeout, readTimeout, null);
    }

    public static String doGet(String url, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) {
        return httpRequest(url, "GET", null, charset, connectTimeout, readTimeout, headerMap);
    }


    //url为不带 ? 号的url
    public static String doGet(String url, Map<String, String> paramMap, String charset, int connectTimeout, int readTimeout) throws IOException {
        String query = buildQuery(paramMap, charset);
        return httpRequest(url + "?" + query, "GET", null, charset, connectTimeout, readTimeout, null);
    }

    public static String doGet(String url, Map<String, String> paramMap, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        String query = buildQuery(paramMap, charset);
        return httpRequest(url + "?" + query, "GET", null, charset, connectTimeout, readTimeout, headerMap);
    }

    //url为不带 ? 号的url
    public static String doGet(String url, Map<String, String> paramMap, String charset, int connectTimeout, int readTimeout, String outputPath) throws IOException {
        String query = buildQuery(paramMap, charset);
        return httpRequest(url + "?" + query, "GET", null, charset, connectTimeout, readTimeout, null, outputPath);
    }

    public static String doPost(String url, Map<String, String> paramMap, String jsonParam, String charset, int connectTimeout, int readTimeout) throws IOException {
        String query = buildQuery(paramMap, charset);
        return httpRequest(url + "?" + query, "POST", jsonParam, charset, connectTimeout, readTimeout, null);
    }

    public static String doPost(String url, Map<String, String> paramMap, String jsonParam, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        String query = buildQuery(paramMap, charset);
        return httpRequest(url + "?" + query, "POST", jsonParam, charset, connectTimeout, readTimeout, headerMap);
    }

    public static String doPost(String url, String jsonParam, String charset, int connectTimeout, int readTimeout) {
        return httpRequest(url, "POST", jsonParam, charset, connectTimeout, readTimeout, null);
    }

    public static String doPost(String url, String jsonParam, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) {
        return httpRequest(url, "POST", jsonParam, charset, connectTimeout, readTimeout, headerMap);
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return String
     */
    private static String httpRequest(String requestUrl, String requestMethod, String outputStr, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap, String outputPath) {
        HttpsURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        String filePath = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("TLSV1"); //使用TLS协议加密
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(connectTimeout);
            httpUrlConn.setReadTimeout(readTimeout);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if(headerMap!=null){
                for(Map.Entry<String, String> entry : headerMap.entrySet()){
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if ("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(charset));
                outputStream.close();
            }

            int code = httpUrlConn.getResponseCode();
            if(code!=200){
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.REQUEST_ERROR);
                throw new BusinessException(ResGlobalExt.REQUEST_ERROR, message);
            }

            String suffix = httpUrlConn.getHeaderField("Content-Type").substring(6);
            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            filePath = outputPath + "tmp_" +  System.currentTimeMillis() + "." + suffix;
            File file = new File(filePath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(data))!=-1){
                fos.write(data, 0, len);
            }
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:", e);
        } finally {// 释放资源
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("https request error:", e);
                }
            }

            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("https request error:", e);
                }
            }

            if(httpUrlConn!=null){
                httpUrlConn.disconnect();
            }
        }

        return filePath;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return String
     */
    private static String httpRequest(String requestUrl, String requestMethod, String outputStr, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(connectTimeout);
            httpUrlConn.setReadTimeout(readTimeout);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(charset));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:", e);
        }
        //System.out.println("WeixinWebUtil.http-url=" + requestUrl);
        //System.out.println("WeixinWebUtil.http-result=" + buffer.toString());
        return buffer.toString();
    }


    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }

    /**
     * 去掉微信昵称的不正常字符，比如表情
     * @param nickname
     * @return
     */
    public static String formatNickname(String nickname) {
        if (StringUtils.isBlank(nickname)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Pattern p = Pattern.compile("[\\u4E00-\\u9FA5\\s\\w\\pP|\\pS]");
        char[] chars = nickname.toCharArray();
        for (char c : chars) {
            Matcher m = p.matcher(c + "");
            builder.append(m.find() ? m.group(0) : "■");
        }
        return builder.toString();
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