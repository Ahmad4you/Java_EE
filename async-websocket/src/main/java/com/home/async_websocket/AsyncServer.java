/**
 * 
 */
package com.home.async_websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.ejb.Singleton;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * 
 * @author Ahmad Alrefai
 */
@Singleton
@ServerEndpoint(value = "/asyncServer")
public class AsyncServer {
    
    private final List<Session> peers = Collections.synchronizedList(new ArrayList<>());
    
    @OnOpen
    public void onOpen(Session peer){
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer){
        peers.remove(peer);
    }
    
    @OnError
    public void onError(Throwable t){
        System.err.println(t.getMessage());
    }
    
    @OnMessage
    public void onMessage(String message, Session peer){
        peers.stream().filter((p) -> (p.isOpen())).forEachOrdered((p) -> {
            p.getAsyncRemote().sendText(message + " - Total peers: " + peers.size());
        });
    }
}
