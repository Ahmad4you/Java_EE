/**
 * 
 */
package com.home.mdb;

import java.util.Date;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@Path("mdbService")
public class MDBService {
    
    @Inject
    private Sender sender;
    
    /*
     *  Generiert eine neue User-Nachricht und sendet diese an die Queue über die Sender-Bean. 
     *  Die Antwort response.resume sendet eine Bestätigung an den Client zurück.
     *  
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void mdbService(@Suspended AsyncResponse response){
        long id = new Date().getTime();
        sender.send(new User("User " + id, "Nachname " + id, 22));
        response.resume("Message sent to the queue");
    }
}