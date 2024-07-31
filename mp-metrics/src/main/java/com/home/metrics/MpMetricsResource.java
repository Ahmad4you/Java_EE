/**
 * 
 */
package com.home.metrics;

import java.util.Collections;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * 
 * @author Ahmad Alrefai
 */

@Path("/metrics")
@RequestScoped
public class MpMetricsResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    
    /*
     * Misst die Zeit, die die Methode benötigt, um ausgeführt zu werden.
     * http://localhost:8080/metrics/timed
     * 
     */
    @Timed(name = "getResourceTimed")
    @Path("/timed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResourceTimed() {
        String response = getResource();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }
    
    /*
     * Misst die Anzahl der Aufrufe der Methode.
     * http://localhost:8080/metrics/metered
     * 
     */
    @Metered(name = "getResourceMetered")
    @Path("/metered")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResourceMetered() {
        String response = getResource();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }
    
    /*
     * erstellt einen HTTP-Client, sendet eine GET-Anfrage an https://google.com und gibt die Antwort als String zurück.
     * 
     */

    private String getResource() {
        Client client = null;
        String response;

        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://google.com");
            //Sendet eine GET-Anfrage und speichert die Antwort.
            response = target.request()
                    .header("Content-Type", "application/json")
                    .get(String.class);
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return response;
    }
}

