package com.imall.iportal.sso.client;

import com.imall.iportal.shiro.sso.utils.SsoUtil;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ygw on 2015/12/2.
 */
public class SalesPosSsoUserFilter extends UserFilter {

    private String appKey;
    private String appSecret;
    private String redirectUrl;

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }


    /**
     * Convenience method for subclasses that merely acquires the {@link #getLoginUrl() getLoginUrl} and redirects
     * the request to that url.
     * <p/>
     * <b>N.B.</b>  If you want to issue a redirect with the intention of allowing the user to then return to their
     * originally requested URL, don't use this method directly.  Instead you should call
     * {@link #saveRequestAndRedirectToLogin(ServletRequest, ServletResponse)
     * saveRequestAndRedirectToLogin(request,response)}, which will save the current request state so that it can
     * be reconstructed and re-used after a successful login.
     *
     * @param request  the incoming <code>ServletRequest</code>
     * @param response the outgoing <code>ServletResponse</code>
     * @throws IOException if an error occurs.
     */
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
        paramMap.put("pos", "Y");
        paramMap.put("appKey", appKey);
        if(StringUtils.hasLength(ssoTicket)) {
            paramMap.put("ticket", ssoTicket);
        }
        paramMap.put("redirectUrl", redirectUrl);

        String loginUrl = getLoginUrl();
        loginUrl = loginUrl + "&sign=" + SsoUtil.md5Hash(paramMap, appSecret) + "&pos=Y";
        if(StringUtils.hasLength(ssoTicket)){
            loginUrl  = loginUrl + "&ticket=" + ssoTicket;
        }
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
