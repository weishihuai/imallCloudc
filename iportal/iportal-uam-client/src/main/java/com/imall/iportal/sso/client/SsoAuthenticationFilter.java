package com.imall.iportal.sso.client;

import com.imall.iportal.shiro.sso.utils.SsoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by yang on 2015-10-22.
 */
public class SsoAuthenticationFilter extends AuthenticatingFilter {

    private String authcTicketParam = "ticket";
    private String failureUrl;

    private String appKey;
    private String appSecret;
    private String redirectUrl;
    private Cookie ssoTicketCookie;

    public void setSsoTicketCookie(Cookie ssoTicketCookie) {
        this.ssoTicketCookie = ssoTicketCookie;
    }

    public void setAuthcTicketParam(String authcTicketParam) {
        this.authcTicketParam = authcTicketParam;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }


    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(authcTicketParam);
        String userName = httpRequest.getParameter("userName");
        return new SsoToken(ticket, userName);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String error = request.getParameter("error"); //todo yang
        String errorDescription = request.getParameter("error_description");
        if(StringUtils.hasLength(error)) {//如果服务端返回了错误 !isEmpty
            WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error + "error_description=" + errorDescription);
            return false;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(authcTicketParam);
        String userName = httpRequest.getParameter("userName");
        String sign = httpRequest.getParameter("sign");
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("appKey", appKey);
        paramMap.put("ticket", ticket);
        paramMap.put("userName", userName);
        if(!SsoUtil.md5Hash(paramMap, appSecret).equals(sign)){
            saveRequestAndRedirectToLogin(request, response); ////参数校验失败
            return false;
        }

        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated()) {
            if(!StringUtils.hasLength(request.getParameter(authcTicketParam))) { //isEmpty
                //如果用户没有身份验证，且没有auth code，则重定向到服务端授权
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String ticket = httpRequest.getParameter(authcTicketParam);
        if(ssoTicketCookie.getDomain()==null){
            ssoTicketCookie.setDomain("");
        }
        ssoTicketCookie.setValue(ticket);
        httpResponse.addCookie(ssoTicketCookie);
        //清理一下session储存的菜单
        Session session = SecurityUtils.getSubject().getSession();
        if(session!=null){
            session.removeAttribute("portalMenu");
        }
        issueSuccessRedirect(httpRequest, httpResponse);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
                                     ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        //${sso.server.authorizeUrl}?client_id=${sso.client.appKey}&redirect_uri=${sso.client.redirectUrl}
        String ssoTicket = SsoUtil.getCookieValueByName(request, "ticket");
        if(!StringUtils.hasLength(ssoTicket)){
            ssoTicket = (String) httpRequest.getParameter("ticket");
        }
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("appKey", appKey);
        if(StringUtils.hasLength(ssoTicket)) {
            paramMap.put("ticket", ssoTicket);
        }
        paramMap.put("redirectUrl", redirectUrl);

        String loginUrl = getLoginUrl();
        loginUrl = loginUrl + "&sign=" + SsoUtil.md5Hash(paramMap, appSecret);
        if(StringUtils.hasLength(ssoTicket)){
            loginUrl  = loginUrl + "&ticket=" + ssoTicket;
        }
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
