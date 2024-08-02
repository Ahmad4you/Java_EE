package com.home.result;

import com.home.result.client.AsyncResultClient;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Response;

/**
 * Asynchrone Verarbeitung in RESTful Diensten:
 *  Wenn eine RESTful API eine Operation durchführt, die viel Zeit in Anspruch nimmt 
 *  (z.B. eine Datenbankabfrage, ein externer API-Aufruf oder komplexe Berechnungen), kann es sinnvoll sein, diese Operation asynchron durchzuführen, 
 *  um den Hauptthread nicht zu blockieren und die Skalierbarkeit und Reaktionsfähigkeit der Anwendung zu verbessern.
 * Microservices-Kommunikation:
 *  In Microservices-Architekturen kommunizieren verschiedene Microservices oft miteinander. Dabei kann es vorkommen, 
 *  dass ein Microservice eine lang laufende Anfrage an einen anderen Microservice sendet. Anstatt den aufrufenden Microservice zu blockieren, 
 *  kann die Anfrage asynchron bearbeitet werden, wodurch die Gesamteffizienz des Systems erhöht wird.
 * Parallelisierung von Aufgaben:
 *  In einer Anwendung, die mehrere unabhängige Aufgaben parallel ausführen kann, ermöglicht die asynchrone Verarbeitung die Parallelisierung 
 *  dieser Aufgaben. Dies führt zu einer besseren Nutzung der Systemressourcen und kann die Gesamtverarbeitungszeit reduzieren.
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("asyncService")
public class AsyncService {

    @Inject
    private AsyncResultClient client;
    
    /*
     * Suspended bedeutet, dass die Antwort asynchron verarbeitet wird.
     * Diese Methode führt die asynchrone Anfrage aus und verarbeitet die Antwort
     * thenApply(this::readResponse): Wandelt die Antwort in einen String um.
     * 
     * http://localhost:8080/ahmad_asyncresult/api/asyncService
     * 
     */

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        try{
            client.getResult().thenApply(this::readResponse).thenAccept(response::resume);
        } catch(Exception e){
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build());
        }        
    }

    private String readResponse(Response response) {
        return response.readEntity(String.class);
    }
}
