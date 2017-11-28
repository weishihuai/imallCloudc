package com.imall.iportal.shiro.realm;

import com.imall.commons.base.global.Global;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.vo.TagAuthVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yang
 */
public class UserRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;//表示此Realm只支持UsernamePasswordToken类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //用户
        SysUser currUser = ServiceManager.sysUserService.getByLoginId(username);
        //当前登录的岗位
        TagAuthVo tagAuthVo = (TagAuthVo)session.getAttribute(Global.SESSION_CACHE_TAG_AUTH);
        if(tagAuthVo ==null){
            //第一次或缓存失效需要重新加载
            SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(currUser.getId());
            if(userOrgJob!=null){
                tagAuthVo = ServiceManager.sysAuthService.getTagAuthVoByJobId(userOrgJob.getJobId());
                tagAuthVo.setUserId(currUser.getId());
                session.setAttribute(Global.SESSION_CACHE_TAG_AUTH, tagAuthVo);
            }
        }

        //返回授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if(tagAuthVo !=null){
            authorizationInfo.setRoles(new HashSet<String>(tagAuthVo.getRolesSet()));
            //权限
            Set<String> permissionsSet = new TreeSet<String>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });
            permissionsSet.addAll(tagAuthVo.getPermissionsSet());
            authorizationInfo.setStringPermissions(permissionsSet);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal(); //得到用户名
/*        String password = new String((char[])token.getCredentials()); // 得到密码*/
        SysUser user = ServiceManager.sysUserService.getUser(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(BoolCodeEnum.NO.equals(user.getIsEnable())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //密码判断交给RetryLimitHashedCredentialsMatcher.java 判断
       /* if(!user.getPassword().equals(password)){
            throw new IncorrectCredentialsException(); //如果密码错误
        }*/

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),  // 盐
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
