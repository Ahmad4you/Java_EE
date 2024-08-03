/**
 * 
 */
package com.home.async_websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import jakarta.ejb.Stateless;
import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import jakarta.ws.rs.container.AsyncResponse;

/**
 * 
 * @author Ahmad Alrefai
 */
@ClientEndpoint
@Stateless
public class AsyncClient {

    private final String asyncServer = "ws://localhost:8080/ahmad_websocket/asyncServer";

    private Session session;
    private AsyncResponse response;

    public AsyncClient() {}

    public AsyncClient(AsyncResponse response) {
        this.response = response;
    }

    public void connect() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(this, new URI(asyncServer));
        } catch (URISyntaxException | DeploymentException | IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        send("Message from client " + new Date().getTime());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if (response != null) {
            response.resume(message);
        }
        close();
    }

    public void send(String message) {
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        } else {
            System.err.println("Session is not open.");
        }
    }

    public void close() {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}