/**
 * 
 */
package com.home.servermock;

import org.glassfish.jersey.client.JerseyClientBuilder;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.SseEventSource;

/**
 * Diese Klasse implementiert einen SSE-Client.
 * 
 * @author Ahmad Alrefai
 */
public class ClientConsumer {

//    public static final Client CLIENT = ClientBuilder.newClient();
    public static final Client CLIENT = new JerseyClientBuilder().build();
    public static final WebTarget WEB_TARGET = CLIENT.target(ServerMock.CONTEXT + ServerMock.BASE_PATH);
    
    public static void main(String[] args) {
        consume();
    }
    
    /*
     * Erstellt eine SSE-Verbindung zum Server.
     * Registriert einen Event-Handler 
     * Sendet in einer Schleife POST-Anfragen an den Server.
     * Wartet jeweils eine Sekunde zwischen den Durchläufen.
     * Der Server empfängt client Anfragen und sendet sie als SSE-Events zurück.
     * 
     */

    private static void consume() {

        try (final SseEventSource sseSource =
                     SseEventSource
                             .target(WEB_TARGET)
                             .build()) {

            sseSource.register(System.out::println);
            sseSource.open();

            for (int counter=0; counter < 5; counter++) {
                System.out.println(" ");
                for (int innerCounter=0; innerCounter < 5; innerCounter++) {
                    WEB_TARGET.request().post(Entity.json("event " + innerCounter));
                }
                Thread.sleep(1000);
            }
            
            CLIENT.close();
            System.out.println("\nAll messages consumed");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
