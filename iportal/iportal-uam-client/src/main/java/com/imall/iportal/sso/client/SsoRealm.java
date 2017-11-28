package com.imall.iportal.sso.client;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by yang on 2015-10-21.
 */
public class SsoRealm extends AuthorizingRealm {

    private String appKey;
    private String appSecret;
    private String redirectUrl;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SsoToken;//表示此Realm只支持ssoToken类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SsoToken ssoToken = (SsoToken) token;
        String ticket = ssoToken.getTicket();
        String username = ssoToken.getPrincipal();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, ticket, getName());
        return authenticationInfo;
    }
}
