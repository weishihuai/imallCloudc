package com.imall.iportal.sso.client;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by ygw on 2017/8/16.
 */
public class SsoSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {//会话创建时触发
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {//会话过期时触发
        String sessionId = session.getId().toString();
        System.out.println("会话过期：" +sessionId);
        WebSocketServer.send(sessionId, "cmd:expiration");
    }

    @Override
    public void onStop(Session session) {//退出/会话过期时触发
        String sessionId = session.getId().toString();
        System.out.println("会话停止：" + sessionId);
        WebSocketServer.send(sessionId, "cmd:logout");
    }

}