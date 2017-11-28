package com.imall.iportal.sso.filter;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.sso.service.SsoService;
import com.imall.iportal.sso.vo.SsoAuthVo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ygw on 2017/1/10.
 */
@Deprecated //原来sso单点登录，想使用ticket换取sid的方式去做，现在只要配置好sso.cookie.domain就行了
public class SsoTicketFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String appKey = request.getParameter("a_key");
        String ssoTicket = request.getParameter("ticket");
        String redirectUrl = request.getParameter("r_url");
        String sign = request.getParameter("sign");
        String sid = null;
        Cookie sidCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            int index = 0;
            for(Cookie cookie: cookies){
                if(cookie.getName()!=null && cookie.getName().equals("sid")){
                    sid = cookie.getValue();
                    sidCookie = cookie;
                    index = index + 1;
                    StringBuilder buff = new StringBuilder();
                    buff.append("SsoTicketFilter cookies= = = = =");
                    buff.append(" index=");
                    buff.append(index);
                    buff.append(" sid=");
                    buff.append(cookie.getValue());
                    buff.append(" domain=");
                    buff.append(cookie.getDomain());
                    buff.append(" path=");
                    buff.append(cookie.getPath());
                    buff.append(" maxAge=");
                    buff.append(cookie.getMaxAge());
                    System.out.println(buff.toString());
                }
                else {
                    response.addCookie(cookie);
                }
            }
        }

        SsoService ssoService = (SsoService)SpringContextHolder.getBean("ssoServiceImpl");
        List<SsoAuthVo> ssoAuthVos = ssoService.getAllSsoAuth();
        for(SsoAuthVo ssoAuthVo: ssoAuthVos){
            StringBuilder buff = new StringBuilder();
            buff.append("allSsoAuth====");
            buff.append("userName=");
            buff.append(ssoAuthVo.getUserName());
            buff.append("sessionId=");
            buff.append(ssoAuthVo.getSessionId());
            System.out.println(buff.toString());
        }

        System.out.println("SsoTicketFilter old cookie sid======= " + sid);
        //sid==null &&
        if(StringUtils.isNoneBlank(ssoTicket) && ssoService.checkSign(appKey, ssoTicket, redirectUrl, sign) && ssoService.checkTicket(ssoTicket)){
            SsoAuthVo ssoAuthVo= ssoService.getByTicket(ssoTicket);
            sid = ssoAuthVo.getSessionId();
            if(sidCookie!=null){
                sidCookie.setValue(sid);
                //sidCookie.setDomain("imall.com.cn");
                sidCookie.setMaxAge(-1);
                System.out.println("SsoTicketFilter add cookie sid======= " + sid);
            }
            else {
                sidCookie = new Cookie("sid", sid);
                //sidCookie.setDomain("imall.com.cn");
                sidCookie.setMaxAge(-1);
                System.out.println("SsoTicketFilter new cookie sid======= " + sid);
            }
        }
        if(sidCookie!=null){
            response.addCookie(sidCookie);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
