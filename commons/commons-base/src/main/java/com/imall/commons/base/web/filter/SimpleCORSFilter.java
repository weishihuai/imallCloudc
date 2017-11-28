package com.imall.commons.base.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Donie on 2015/11/4.
 * 这个同源策略应该是每个子应用都要共享的，可以放到commonse-base项目中
 */
//apache-tomcat-7.0.73 以上版本 自带了 org.apache.catalina.filters.CorsFilter
//http://tomcat.apache.org/tomcat-7.0-doc/config/filter.html#CORS_Filter
@Deprecated
public class SimpleCORSFilter implements Filter {


    //FilterConfig可用于访问Filter的配置信息
    private FilterConfig config;

    //实现初始化方法
    public void init(FilterConfig config) {
        this.config = config;
    }

    //实现销毁方法
    public void destroy() {
        this.config = null;
    }


    // Configurable origin for CORS - default: * (all)
    private String origin;

    public String getOrigin() {
        if(origin==null){
            origin = config.getInitParameter("origin");
        }
        return origin==null?"*":origin;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Origin", getOrigin());
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, X-E4M-With");
        //跨域解决后，如果还要操作Cookie，还得继续补增响应头：
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }
}
