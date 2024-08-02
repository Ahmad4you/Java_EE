package com.home.result.remote;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.entity.User;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * http://localhost:8080/ahmad_asyncresult/api/userService
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("userService")
public class UserService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response userService(){
        try {
            TimeUnit.SECONDS.sleep(5); // Simuliert eine Verzögerung von 5 Sekunden
            long id = new Date().getTime();
            return Response.ok(new User(id, "User " + id)).build();
        } catch (InterruptedException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
}
