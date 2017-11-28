package com.imall.iportal.frontend.wechat.common.filter;

import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WeChatAutoLoginFilter extends AbstractExcludeUrlFilter {

    @Override
    public void realFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();

            String userAgent = httpRequest.getHeader("User-Agent");
            String microMessenger = "MicroMessenger/";
            if (userAgent.indexOf(microMessenger) > 0) {
                if (weChatUserProxy == null) {
//                    OAuthAPI.OAuthIfNecessary(httpRequest, httpResponse);
//                    return;
                }
            } else {
                if (!httpRequest.getRequestURI().contains("/wechat/agentError")){
//                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/wechat/agentError.html");
//                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
