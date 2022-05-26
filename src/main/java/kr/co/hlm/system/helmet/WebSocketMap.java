package kr.co.hlm.system.helmet;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint("/helmets/main")
public class WebSocketMap {
    public static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session s) {
        System.out.println("open session : " + s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
            System.out.println("session open : " + s);
        }else {
            System.out.println("already access Session");
        }
    }


    @OnMessage
    public void onMessage(String msg, Session session) throws Exception{
        System.out.println("receive message : " + msg);
        for(Session s : clients) {
            System.out.println("send data : " + msg);
            s.getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
        clients.remove(s);
    }
}
