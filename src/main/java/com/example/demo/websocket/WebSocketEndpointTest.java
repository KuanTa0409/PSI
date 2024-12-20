package com.example.demo.websocket;


import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket")
public class WebSocketEndpointTest {
	
	// 用來存放WebSocket已連接的Socket
    static CopyOnWriteArraySet<Session> sessions;
    @OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException, EncodeException {
        System.out.println("message = " + message);
        
        for (Session s : sessions) {    //對每個連接的Client傳送訊息
            if (s.isOpen()) {
                
                s.getAsyncRemote().sendText(message);
            }
        }
    }
    @OnOpen
    public void onOpen(Session session) {
        //紀錄連接到sessions中
        if (sessions == null) {
            sessions = new CopyOnWriteArraySet<Session>();
            System.out.println("新連線:" + session.getId());
        }
        sessions.add(session);
        System.out.println("目前連線:" + sessions.size());
    }
    @OnClose
    public void onClose(Session session) {
        //將連接從sessions中移除
        if (sessions == null) {
            sessions = new CopyOnWriteArraySet<Session>();
            System.out.println("關閉連線:" + session.getId());
        }
        sessions.remove(session); 
        System.out.println("目前連線:" + sessions.size());
    }
}
