/**
 * 
 */
package com.home.async_websocket;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;


/**
 * eine Nachricht über eine WebSocket-Verbindung senden
 * http://localhost:8080/ahmad_websocket/asyncService
 * 
 * Echtzeit-Webanwendungen: Chat-Anwendungen
 * Aktienhandel, Marktüberwachung
 * Multiplayer-Spiele
 * Microservices-Architekturen
 * Event-Streaming
 * (SSE): Eine Alternative zu Websockets, bei der Server Ereignisse an den Client sendet.
 * JAX-RS für RESTful Web Services: Integration von Websockets und asynchronen RESTful Diensten für Echtzeitanwendungen.
 * 
 * Dashboard für eine Finanzanwendung, das Echtzeit-Marktdaten anzeigt
 * Der AsyncServer sendet aktuelle Marktinformationen an alle verbundenen Clients. Der AsyncClient verbindet sich mit diesem Server 
 * und empfängt die Daten, um sie im Dashboard anzuzeigen. Der AsyncService-Endpunkt ermöglicht es, einen neuen Websocket-Client zu initialisieren, 
 * der sich mit dem Server verbindet und die Daten empfängt.
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("asyncService")
public class AsyncService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncService(@Suspended AsyncResponse response) {
        AsyncClient client = new AsyncClient(response);
        client.connect();
    }
}
