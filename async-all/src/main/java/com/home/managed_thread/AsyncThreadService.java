/**
 * 
 */
package com.home.managed_thread;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * http://localhost:8080/ahmad_asyncall/api/asyncThreadService
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("asyncThreadService")
public class AsyncThreadService {

    @Inject
    private UserThreadBean userBean;

    @Resource(name = "LocalManagedThreadFactory")
    private ManagedThreadFactory factory;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncService(@Suspended AsyncResponse response) {
        Thread thread = factory.newThread(() -> {
            response.resume(Response.ok(userBean.getUser()).build());
        });
        
        thread.setName("Managed Async Task");
        thread.setPriority(Thread.MIN_PRIORITY); // Die Priorität des Threads wird auf die niedrigste Stufe gesetzt.
        thread.start();
    }

}
