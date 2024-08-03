/**
 * 
 */
package com.home.proxy_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



/**
 * http://localhost:8080/ahmad_asyncall/api/asyncProxytaskService
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@Path("asyncProxytaskService")
public class AsyncProxytaskService {

    @Inject
    private ExecutorProxy executor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncService(@Suspended AsyncResponse response) {
        Future<User> result = executor.submit(new AsyncProxyTask());
        try {
            User user = result.get(); // Warten auf den Abschluss der Aufgabe und das User-Objekt abrufen
            response.resume(Response.ok(user).build());
        } catch (InterruptedException | ExecutionException e) {
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }

}