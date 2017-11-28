package com.imall.iportal.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yang
 */

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if(token instanceof UsernamePasswordToken){
            String username = (String)token.getPrincipal();
            //retry count + 1
            AtomicInteger retryCount = passwordRetryCache.get(username);
            if(retryCount == null) {
                retryCount = new AtomicInteger(0);
                passwordRetryCache.put(username, retryCount);
            }
            //错误登录次数
            if(retryCount.incrementAndGet() > 32) {
                //if retry count > 5 throw
                throw new ExcessiveAttemptsException();
            }

           // return true;
            //判断密码
            boolean matches = super.doCredentialsMatch(token, info);
            if(matches) {
                //clear retry count
                passwordRetryCache.remove(username);
            }
            return matches;
        }
        return true;
    }
}


