package com.imall.iportal.shiro.session;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.shiro.service.SessionCacheService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by ygw on 2015/11/25.
 */
public class MCSessionDAO extends AbstractSessionDAO {


    private String appKey;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        sessionId = sessionCacheService.createSession(appKey, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        return sessionCacheService.getSession(appKey, sessionId.toString());
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        sessionCacheService.updateSession(appKey, session);
    }

    @Override
    public void delete(Session session) {
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        sessionCacheService.deleteSession(appKey, session);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        SessionCacheService sessionCacheService = (SessionCacheService)SpringContextHolder.getBean("sessionCacheService");
        Map<String, Session> map = sessionCacheService.getAllSession();
        return map.values();
    }
}