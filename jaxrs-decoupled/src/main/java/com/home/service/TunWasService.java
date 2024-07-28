/**
 * 
 */
package com.home.service;

import java.util.logging.Logger;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;


/**
 * http://localhost:8080/ahmad_decoupled/api/tunWasService/test
 * http://localhost:8080/ahmad_decoupled/api/tunWasService/tunWasCoupled?vorname=developer&nachname=meister
 * http://localhost:8080/ahmad_decoupled/api/userService/getUserCoupled?vorname=developer&nachname=meister
 * 
 * 
 * 
 * @author Ahmad Alrefai
 */

@Path("tunWasService")
public class TunWasService {
	private static final Logger LOGGER = Logger.getLogger(TunWasService.class.getName());
    
    private final String hostURI = "http://localhost:8080/";
    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target(hostURI + "ahmad_decoupled/api/");
    }  
    
    /*
     * Beide Methoden senden Anfragen an einen anderen Service ( UserService) und geben dessen Antwort zurück. 
     * Falls eine ProcessingException auftritt (z.B. bei einem Timeout), 
     * wird ein 408 (Request Timeout) Status zurückgegeben.
     * 
     */
    
    @GET
    @Path("tunWasCoupled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tunWasCoupled(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname) {
        WebTarget service = target.path("userService/getUserCoupled")
                .queryParam("vorname", vorname)
                .queryParam("nachname", nachname);

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(response.readEntity(String.class)).build();
    }
    
    /*
     * curl -X POST "http://localhost:8080/ahmad_decoupled/api/tunWasService/tunWasDecoupled" 
     * -H "Content-Type: application/json" 
     * -d "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 30}"
     * 
     * -H Header
     * -d Data Eingabe
     * 
     */
    @POST
    @Path("tunWasDecoupled")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tunWasDecoupled(User user){
        WebTarget service = target.path("userService/getUserDecoupled");

        Response response;
        try {
            response = service.request().header("User", Entity.json(user)).post(Entity.json(user));
        } catch (ProcessingException e) {
            LOGGER.severe("ProcessingException: " + e.getMessage());
            return Response.status(408).build();
        }

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            LOGGER.severe("Fehler beim Aufruf des externen Services: " + response.getStatus());
            return Response.status(response.getStatus()).build();
        }

        return Response.ok(response.readEntity(String.class)).build();
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "TunWasService is working";
    }
    
    
    
}
