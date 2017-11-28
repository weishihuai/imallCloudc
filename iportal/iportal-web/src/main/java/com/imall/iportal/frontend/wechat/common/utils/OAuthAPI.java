package com.imall.iportal.frontend.wechat.common.utils;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.QRCodeTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysParamInf;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import com.imall.iportal.core.weshop.utils.WeChatWebUtil;
import com.imall.iportal.core.weshop.vo.WeChatUserResult;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OAuthAPI {
    private static Logger logger = Logger.getLogger(OAuthAPI.class);

    public static void OAuthIfNecessary(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WeChatUserProxy loginUser = WebContextFactory.getWebContext().getFrontEndUser();
        //已经登录，则不再做任何操作了
        if (loginUser != null) {
            return;
        }

        boolean isValidCode = true;
        String code = request.getParameter("code");
        String serviceUrl = request.getRequestURL().toString();
        if (StringUtils.isNotBlank(request.getParameter("router"))) {
            serviceUrl = serviceUrl + "#/" + request.getParameter("router");
        }

        //检查是否已验证或者验证是否通过
        if (code == null) {
            isValidCode = false;
        }
        //未授权，重定向到授权页面
        if (!isValidCode) {
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
            SysParamInf appIdInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_APP_ID);
            if (appIdInf == null || StringUtils.isBlank(appIdInf.getParamValueStr())) {
                logger.error("请配置系统参数 WE_CHAT_APP_ID");
                throw new BusinessException(ResGlobal.COMMON_ERROR, "请配置系统参数 WE_CHAT_APP_ID");
            }
            StringBuilder oauth_url = new StringBuilder();
            oauth_url.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
                    .append("appid=").append(appIdInf.getParamValueStr())
                    .append("&redirect_uri=").append(java.net.URLEncoder.encode(tempContextUrl + "iportal/wechat/codeToLoginServlet", "GB2312"))
                    .append("&response_type=code")
                    .append("&scope=snsapi_base")
                    .append("&state=").append(serviceUrl)
                    .append("#wechat_redirect");
            response.sendRedirect(oauth_url.toString());
            return;
        }
        //获得code，执行登录
        doLoginAction(code, request, response);
    }

    private static void doLoginAction(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map responseMap = OAuthAPI.getAccessToken(code);
        String openId = (String) responseMap.get("openid");
        logger.info("获取到的openId=" + openId);
        if (StringUtils.isBlank(openId)) {
            return;
        }
        WeChatUser weChatUser = ServiceManager.weChatUserService.findByOpenId(openId);
        if (weChatUser == null) {
            WeChatUserResult weChatUserResult = ServiceManager.weChatApiService.getUserByOpenId(openId);
            String nickName = StringUtils.isBlank(weChatUserResult.getNickname()) ? weChatUserResult.getOpenid() : WeChatWebUtil.formatNickname(weChatUserResult.getNickname());
            weChatUser = new WeChatUser();
            weChatUser.setMobile("");
            weChatUser.setOpenId(openId);
            weChatUser.setIsSubscribe(BoolCodeEnum.toCode(weChatUserResult.getSubscribe() == 1));
            weChatUser.setNickName(nickName);
            weChatUser.setUserName(nickName);
            ServiceManager.weChatUserService.save(weChatUser);
        }
        WeChatUserProxy weChatUserProxy = CommonUtil.copyProperties(weChatUser, new WeChatUserProxy());
        weChatUserProxy.setMember(false);
        WebContextFactory.getWebContext().setFrontEndUser(weChatUserProxy);
        response.sendRedirect(request.getParameter("state"));
    }

    /**
     * 获取授权令牌
     */
    private static Map getAccessToken(String code) {
        SysParamInf appIdInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_APP_ID);
        SysParamInf secretInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_SECRET);
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(urlStr)
                .append("?appid=").append(appIdInf.getParamValueStr())
                .append("&secret=").append(secretInf.getParamValueStr())
                .append("&code=").append(code)
                .append("&grant_type=authorization_code");

        String result = null;
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流并释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JsonBinder.toBeanObject(result, HashMap.class);
    }

}