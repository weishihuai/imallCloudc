package com.imall.iportal.sso.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by ygw on 2017/8/16.
 */
@ServerEndpoint(value = "/websocket/login",configurator=GetHttpSessionConfigurator.class)
public class WebSocketServer {

    private static final Log log = LogFactory.getLog(WebSocketServer.class);

    private static final Map<String, WebSocketServer> connections = new ConcurrentHashMap<String, WebSocketServer> ();

    private String sessionId;
    private Session session;


    /*当websocket的客户端连接到服务器时候，这个方法就会执行，并且传递一个session会话对象来
     我们拿到这话session，就是可以给客户端发送消息*/
    @OnOpen
    public void start(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.sessionId = httpSession.getId();
        connections.put(sessionId, this);
    }


    /*客户端被关闭时候，就会自动会调用这个方法*/
    @OnClose
    public void end() {
        connections.remove(sessionId);
    }

    /*客户端给服务器发送消息，这个方法会自动调用，并且可以拿到发送过来的数据*/
    @OnMessage
    public void incoming(String message) {
        log.info("Chat Info: " + message);
        // Never trust the client
        if(message.equals("cmd:close")){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*发生了异常自动执行*/
    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }

    public static void send(String sessionId, String msg) {
        WebSocketServer webSocketServer = connections.get(sessionId);
        if(webSocketServer!=null){
            try {//如果这个client已经在线
                synchronized (webSocketServer) {
                    webSocketServer.session.getBasicRemote().sendText(msg);//发送消息
                }
            } catch (IOException e) {//如果这个client不在线
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(webSocketServer.sessionId);
                try {
                    webSocketServer.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
            }
        }
    }

}
