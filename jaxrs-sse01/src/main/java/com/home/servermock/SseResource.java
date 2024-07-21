/**
 * 
 */
package com.home.servermock;

import java.io.IOException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;



/**
 * Diese Klasse definiert den SSE-Endpunkt.
 * SseEventSink-Objekt zum Senden von Events.
 * 
 * @author Ahmad Alrefai
 */
@Path(ServerMock.BASE_PATH)
public class SseResource {

    private static volatile SseEventSink SINK = null;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getMessageQueue(@Context SseEventSink sink) { // Methode zum Einrichten der SSE-Verbindung.
        SseResource.SINK = sink;
    }
    
    /*
     * Methode zum Hinzufügen und Senden von Nachrichten an den Client.
     */

    @POST
    public void addMessage(final String message, @Context Sse sse) throws IOException {
        if (SINK != null) {
            SINK.send(sse.newEventBuilder()
                    .name("sse-message")
                    .id(String.valueOf(System.currentTimeMillis()))
                    .data(String.class, message)
                    .comment("")
                    .build());
        }
    }
}

