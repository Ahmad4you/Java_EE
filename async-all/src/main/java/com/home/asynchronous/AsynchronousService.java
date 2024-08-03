/**
 * 
 */
package com.home.asynchronous;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
 * http://localhost:8080/ahmad_asyncall/api/asynchronousService
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("asynchronousService")
public class AsynchronousService {
    
    @Inject
    private UserAsynchronousBean userAsynchronousBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asynchronousService(@Suspended AsyncResponse response){
        try {
            Future<User> result = userAsynchronousBean.getUser();
            
            while(!result.isDone()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            response.resume(Response.ok(result.get()).build());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

