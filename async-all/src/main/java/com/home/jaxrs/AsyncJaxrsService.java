/**
 * 
 */
package com.home.jaxrs;

import java.util.concurrent.TimeUnit;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * http://localhost:8080/ahmad_asyncall/api/asyncJaxrsService
 * 
 * @author Ahmad Alrefai
 */

@Stateless
@Path("asyncJaxrsService")
public class AsyncJaxrsService {
    
    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        target = client.target("http://localhost:8080/ahmad_asyncall/api/remoteUser");
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncJaxrsService(@Suspended AsyncResponse response){
        target.request().async().get(new InvocationCallback<Response>() {
            @Override
            public void completed(Response rspns) {
                response.resume(rspns);
            }

            @Override
            public void failed(Throwable thrwbl) {
                response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(thrwbl.getMessage()).build());
            }
        });
                
    }
    
}
