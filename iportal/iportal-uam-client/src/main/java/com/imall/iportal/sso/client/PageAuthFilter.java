package com.imall.iportal.sso.client;

import com.imall.iportal.core.main.commons.ServiceManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygw on 2017/8/16.
 */
public class PageAuthFilter extends AccessControlFilter {

    private String redirectUrl = "/";

    private List<String> excludeJsList = new ArrayList<>(); //排除

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setExcludeJss(String excludeJss) {
        if(StringUtils.isNotBlank(excludeJss)){
            String[] jss = excludeJss.split(",");
            for(String js: jss){
                if(StringUtils.isNotBlank(js)){
                    excludeJsList.add(js);
                }
            }
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse res, Object mappedValue) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        String uri = request.getRequestURI();
        String js = uri.substring(uri.lastIndexOf("/") + 1);
        if(excludeJsList.contains(js) || js.endsWith(".js.map")){
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        List<String> list = ServiceManager.sysResourceService.findByRouterTemplateJs("static/dist/" + js);
        for(String permissionCode: list){
            if(subject.isPermitted(permissionCode)){
                return true;
            }
        }
        System.out.println("没有授权，请给这个资源授权，或者在beans.xml配置exclude排除：" + uri);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, redirectUrl);
        return false;
    }
}
