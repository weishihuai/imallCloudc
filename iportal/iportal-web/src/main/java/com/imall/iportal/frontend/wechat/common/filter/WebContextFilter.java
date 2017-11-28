package com.imall.iportal.frontend.wechat.common.filter;

import com.imall.iportal.frontend.wechat.common.web.DefaultWebContext;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebContextFactory设置
 * User: lxd
 * Date: 2010-12-26
 * Time: 19:54:30
 */
public class WebContextFilter extends HttpServlet implements Filter {
    private static final long serialVersionUID = 5454135885035635342L;

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        DefaultWebContext ctx = (DefaultWebContext) WebContextFactory.getWebContext();
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
        	ctx.setRequest((HttpServletRequest)request);
            ctx.setResponse((HttpServletResponse)response);
            ctx.setSession(((HttpServletRequest) request).getSession());
        }
        chain.doFilter(request, response);
    }


    public void destroy() {
        
    }
}
