package com.imall.iportal.frontend.wechat.common.web;

import com.imall.commons.base.global.Global;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 系统默认对IWebContext实现
 * User: lxd
 * Date: 2010-12-26
 * Time: 19:18:16
 */
public class DefaultWebContext implements IWebContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;


    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setSessionAttr(String key, Object value) {
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    public Object getSessionAttr(String key) {
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    public void removeSessionAttr(String key) {
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public void setRequestAttr(String key, Object value) {
        if (request != null) {
            request.setAttribute(key, value);
        }
    }

    public Object getRequestAttr(String key) {
        if (request != null) {
            request.getAttribute(key);
        }
        return null;
    }

    public void removeRequestAttr(String key) {
        if (request != null) {
            request.removeAttribute(key);
        }
    }

    @Override
    public void setFrontEndUser(WeChatUserProxy weChatUserProxy) {
        if (session != null) {
            session.setAttribute(Global.FRONT_USER, weChatUserProxy);
        }
    }

    @Override
    public WeChatUserProxy getFrontEndUser() {
        if (session != null) {
            return (WeChatUserProxy) session.getAttribute(Global.FRONT_USER);
        }
        return null;
    }

    @Override
    public String getDomain() {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
        return tempContextUrl.replace("http://", "");
    }

    @Override
    public void deleteCookie(String path, String cookieName) {
        Cookie cookie = getCookie(cookieName);
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath(path);
            cookie.setDomain(this.getDomain());
            response.addCookie(cookie);
        }
    }

    @Override
    public Boolean getFrontendUserIsMember() {
        return getFrontEndUser() == null ? false : getFrontEndUser().getMember();
    }

    @Override
    public void setFrontendUserIsMember(Boolean isMember) {
        if(getFrontEndUser() != null){
            WeChatUserProxy loginUser = getFrontEndUser();
            loginUser.setMember(isMember);
            setFrontEndUser(loginUser);
        }
    }

    @Override
    public Long getFrontEndUserId() {
        return getFrontEndUser() == null ? null : getFrontEndUser().getId();
    }

    public void setShopId(Long shopId) {
        if (session != null) {
            //切换门店的时候，判断当前登录用户是否是当前门店的会员
            WeChatUserProxy weChatUserProxy = getFrontEndUser();
            this.setFrontendUserIsMember(false);
            if (weChatUserProxy != null && shopId != null){
                Fans fans = ServiceManager.fansService.findByWeChatUserIdAndShopId(weChatUserProxy.getId(), shopId);
                this.setFrontendUserIsMember(fans != null && BoolCodeEnum.YES == BoolCodeEnum.fromCode(fans.getIsMember()));
            }
            session.setAttribute(Global.FRONT_END_SHOP_ID, shopId);
        }
    }

    public Long getShopId() {
        if (session != null) {
            return (Long) session.getAttribute(Global.FRONT_END_SHOP_ID);
        }
        return null;
    }

    @Override
    public String getHost() {
        if (request != null) {
            return request.getRemoteHost();
        }
        return null;
    }

    @Override
    public String getRemoteAddr() {
        if (request != null) {
            return request.getRemoteAddr();
        }
        return null;
    }

    @Override
    public Cookie getCookie(String cookieName) {
        if (getCookies() != null) {
            for (Cookie c : getCookies()) {
                if (c.getName().equalsIgnoreCase(cookieName)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String getCookieValue(String cookieName) {
        Cookie cookie = getCookie(cookieName);
        return cookie == null ? "" : cookie.getValue();
    }

    @Override
    public Cookie[] getCookies() {
        return request.getCookies();
    }

    @Override
    public void setCookie(Cookie c) {
        response.addCookie(c);
    }

    @Override
    public void expireCookie(String cookieName) {
        Cookie cookie = getCookie(cookieName);
        if (cookie != null) {
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
    }

    public boolean checkValidateCode(String input, boolean remove) {
        String validateCode = (String) session.getAttribute(Global.VALIDATE_CODE);
        boolean result = StringUtils.equalsIgnoreCase(validateCode, input);
        if (remove || result) {
            session.removeAttribute(Global.VALIDATE_CODE);
        }
        return result;
    }

}
