package com.imall.iportal.shiro.service;

import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.vo.TagAuthVo;
import com.imall.iportal.shiro.vo.PermissionContextVo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ygw on 2016/5/17.
 */
@Component
public class SessionCacheService{


    private Cache authCache = new MapCache("auth-cache", new ConcurrentHashMap());
    private Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    public Session getSession(String appKey, String sessionId) {
        //System.out.println("appKey=" + appKey + "   sessionId=" + sessionId + "    getSession");
        return sessionMap.get(sessionId.toString());
    }

    public String createSession(String appKey, Session session) {
        //System.out.println("appKey=" + appKey + "   sessionId=" + session.getId().toString()  + "    createSession");
        sessionMap.put(session.getId().toString(), session);
        return session.getId().toString();
    }

    public String updateSession(String appKey, Session session) {
        //System.out.println("appKey=" + appKey + "   sessionId=" + session.getId().toString() + "    updateSession");
        sessionMap.put(session.getId().toString(), session);
        return session.getId().toString();
    }

    public Boolean deleteSession(String appKey, Session session) {
        //System.out.println("appKey=" + appKey + "   sessionId=" + session.getId().toString()  + "    deleteSession");
        sessionMap.remove(session.getId().toString());
        return true;
    }

    public Map<String, Session> getAllSession() {
        return sessionMap;
    }

    public PermissionContextVo getPermissions(String appKey, String username) {
        // System.out.println("appKey=" + appKey);
        //用户
        SysUser currUser = ServiceManager.sysUserService.getByLoginId(username);
        //当前登录的岗位
        TagAuthVo tagAuthVo = (TagAuthVo)authCache.get(currUser.getId());
        if(tagAuthVo ==null){ //TODO yang
            //第一次或缓存失效需要重新加载
            SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(currUser.getId());
            if(userOrgJob!=null){
                tagAuthVo = ServiceManager.sysAuthService.getTagAuthVoByJobId(userOrgJob.getJobId());
                tagAuthVo.setUserId(currUser.getId());
                authCache.put(currUser.getId(), tagAuthVo);
            }
        }

        PermissionContextVo permissionContext = new PermissionContextVo();
        if(tagAuthVo !=null){
            permissionContext.setRolesSet(tagAuthVo.getRolesSet());
            permissionContext.setPermissionsSet(tagAuthVo.getPermissionsSet());
        }
        return permissionContext;
    }

    /**
     * 删除授权的cache
     * @param username
     * @return
     */
    public Boolean clearUserAuthCache(String username){
        //用户
        SysUser currUser = ServiceManager.sysUserService.getByLoginId(username);
        authCache.remove(currUser.getId());
        return true;
    }
}
