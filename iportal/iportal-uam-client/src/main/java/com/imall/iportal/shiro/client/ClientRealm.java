package com.imall.iportal.shiro.client;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.shiro.service.SessionCacheService;
import com.imall.iportal.shiro.vo.PermissionContextVo;
import com.imall.iportal.sso.client.SsoToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yang on 2015-11-17.
 */
public class ClientRealm extends AuthorizingRealm {

    private String appKey;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return !(token instanceof SsoToken); //表示此Realm不支持ssoToken类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        PermissionContextVo context = sessionCacheService.getPermissions(appKey, username);
        authorizationInfo.setRoles(new HashSet<String>(context.getRolesSet()));
        //权限
        Set<String> permissionsSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        permissionsSet.addAll(context.getPermissionsSet());
        authorizationInfo.setStringPermissions(permissionsSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //永远不会被调用
        throw new UnsupportedOperationException("永远不会被调用");
    }
}

