package com.imall.iportal.frontend.wechat.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


public abstract class AbstractExcludeUrlFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!this.doInclude(request, response, filterChain)) {
            filterChain.doFilter(request, response);
            return;
        }

        //对配置的excludeUrls参数包含的URL进行过滤,例如：/iMall/;/iMall/*;/iMall/*.jpg
        if (this.doExclude(request, response, filterChain)) {
            filterChain.doFilter(request, response);
            return;
        }
        realFilter(request, response, filterChain);
    }

    public abstract void realFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public boolean doExclude(ServletRequest request, ServletResponse response,
                             FilterChain filterChain) throws ServletException,
            IOException {
        String excludeUrls = this.getInitParameter("excludeUrls");
        if(excludeUrls == null){
        	return false;
        }
        String[] excludes = excludeUrls.split(";");

        if(request instanceof HttpServletRequest){
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	
	        String path = httpRequest.getServletPath();
	        //需要忽略的URL
	        for (int i = 0; i < excludes.length; i++) {
	            String regx = excludes[i].replaceAll("\\.", "\\\\.");
	            regx = regx.replaceAll("\\*", "\\.*");
	            if (excludes[i].endsWith("/")) {
	                regx += ".*";
	            }
	
	            if (path.matches(regx)) {
	                return true;
	            }
	        }
        }
        return false;

    }

    public boolean doInclude(ServletRequest request, ServletResponse response,
                             FilterChain filterChain) throws ServletException,
            IOException {
        String includeUrls = this.getInitParameter("includeUrls");
        if(includeUrls == null){
        	return false;
        }
        String[] includes = includeUrls.split(";");

        if(request instanceof HttpServletRequest){
	        HttpServletRequest httprequest = (HttpServletRequest) request;
	        String path = httprequest.getServletPath();
	        //需要加载的URL
	        for (int i = 0; i < includes.length; i++) {
	            String regx = includes[i].replaceAll("\\.", "\\\\.");
	            regx = regx.replaceAll("\\*", "\\.*");
	            if (includes[i].endsWith("/")) {
	                regx += ".*";
	            }
	
	            if (path.matches(regx)) {
	                return true;
	            }
	        }
        }
        return false;
    }

    public String getInitParameter(String name) {
        return filterConfig.getInitParameter(name);
    }

    public Enumeration getInitParameterNames() {
        return filterConfig.getInitParameterNames();
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    @Override
	public void destroy() {
	}
}

