/**
 * 
 */
package com.home.jaxrs;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@Path("remoteUser")
public class UserJaxrsBean {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response remoteUser() {
        long id = new Date().getTime();
        try {
            TimeUnit.SECONDS.sleep(5);
            return Response.ok(new User("User " + id, "Nachname " + id, 22)).build();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return Response.ok(new User("Error " + id, "InterruptedException", 1)).build();
        }
    }

}