/**
 * 
 */
package com.home.microprofile;

import java.util.Collections;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * eine REST-Ressourcenklasse, die mehrere Endpunkte zur Verfügung stellt, um Begrüßungsnachrichten zu verwalten.
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/ahmad_healthcheck/greet
 *
 * Get greeting message for Diana:
 * curl -X GET http://localhost:8080/ahmad_healthcheck/greet/Diana
 *
 * Change greeting
 * curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Howdy"}' http://localhost:8080/ahmad_healthcheck/greet/greeting
 *
 * The message is returned as a JSON object.
 */
@Path("/greet")
@RequestScoped
public class GreetResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

   
    private final GreetingProvider greetingProvider;
    
    // No-args constructor for proxying
    public GreetResource() {
        this.greetingProvider = null;  // Default initialization for proxying
    }

    /**
     * Using constructor injection to get a configuration property.
     * By default this gets the value from META-INF/microprofile-config
     *
     * @param greetingConfig the configured greeting message
     */
    @Inject
    public GreetResource(GreetingProvider greetingConfig) {
        this.greetingProvider = greetingConfig;
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDefaultMessage() {
        return createResponse("World");
    }

    /**
     * Diese Methode gibt eine Begrüßungsnachricht für den angegebenen Namen zurück.
     * 
     * http://localhost:8080/ahmad_healthcheck/greet/Ahmad
     * http://localhost:8080/greet/Ahmad
     *
     * @param name the name to greet
     * @return {@link JsonObject}
     */
    
    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getMessage(@PathParam("name") String name) {
        return createResponse(name);
    }

    /**
     * Diese Methode aktualisiert die Begrüßungsnachricht. 
     * Wenn das JSON-Objekt keinen Schlüssel greeting enthält, wird ein Fehler zurückgegeben. 
     * Andernfalls wird die Begrüßungsnachricht aktualisiert.
     *
     * @param jsonObject JSON containing the new greeting
     * @return {@link Response}
     */
   
    @Path("/greeting")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGreeting(JsonObject jsonObject) {

        if (!jsonObject.containsKey("greeting")) {
            JsonObject entity = JSON.createObjectBuilder()
                    .add("error", "No greeting provided")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }

        String newGreeting = jsonObject.getString("greeting");

        greetingProvider.setMessage(newGreeting);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    /**
     * Diese Methode erstellt eine JSON-Antwort mit der Begrüßungsnachricht und dem angegebenen Namen. 
     * Sie verwendet die greetingProvider-Instanz, um die Begrüßungsnachricht zu holen und fügt den Namen hinzu.
     * 
     * @param who
     * @return
     */

    private JsonObject createResponse(String who) {
        String msg = String.format("%s %s!", greetingProvider.getMessage(), who);

        return JSON.createObjectBuilder()
                .add("message", msg)
                .build();
    }
}
