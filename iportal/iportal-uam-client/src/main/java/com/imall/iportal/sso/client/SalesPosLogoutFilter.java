package com.imall.iportal.sso.client;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ygw on 2017/8/2.
 */
public class SalesPosLogoutFilter extends LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        String redirectUrl = this.getRedirectUrl(request, response, subject);
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String succeedWhoId = (String) httpRequest.getParameter("succeedWhoId");

        try {
            subject.logout();
        } catch (SessionException var6) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
        }

        Subject newSubject = this.getSubject(request, response);
        newSubject.getSession().setAttribute("succeedWhoId", succeedWhoId);
        this.issueRedirect(request, response, redirectUrl);
        return false;
    }
}
