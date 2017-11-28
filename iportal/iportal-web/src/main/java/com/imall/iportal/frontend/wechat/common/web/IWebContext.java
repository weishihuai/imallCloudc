package com.imall.iportal.frontend.wechat.common.web;

import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对session，request,application的attribute进行修改
 * User: lxd
 * Date: 2010-12-26
 * Time: 19:04:01
 */
public interface IWebContext {
    HttpServletRequest getRequest();
    HttpServletResponse getResponse();
    void setSessionAttr(String key, Object value);
    Object getSessionAttr(String key);
    void removeSessionAttr(String key);

    void setRequestAttr(String key, Object value);
    Object getRequestAttr(String key);
    void removeRequestAttr(String key);

    void setFrontEndUser(WeChatUserProxy weChatUserProxy);
    WeChatUserProxy getFrontEndUser();
    Long getFrontEndUserId();

    void setShopId(Long shopId);
    Long getShopId();
    String getHost();
    String getRemoteAddr();

    Cookie getCookie(String cookieName);
    String getCookieValue(String cookieName);
    Cookie[] getCookies();
    void setCookie(Cookie c);
    void expireCookie(String cookieName);

    /**
     * 检验验证码的正确
     * @param input 输入值
     * @param remove true:无论匹配验证码正确还是错误都进行清除 false:只在匹配成功后才清除
     * @return
     */
    boolean checkValidateCode(String input, boolean remove);

    /**
     * 获取域名
     * @return
     */
    String getDomain();

    /**
     * 删除cookie
     * @param path
     * @param cookieName
     */
    void deleteCookie(String path, String cookieName);

    /**
     * 获取微信登录用户是否是会员
     * @return
     */
    Boolean getFrontendUserIsMember();

    /**
     * 设置微信登录用户是否会员
     * @param isMember
     */
    void setFrontendUserIsMember(Boolean isMember);
}
