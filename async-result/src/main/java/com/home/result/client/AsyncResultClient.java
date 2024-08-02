package com.home.result.client;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class AsyncResultClient {

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        target = client.target("http://localhost:8080/ahmad_asyncresult/api/userService");
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    public CompletionStage<Response> getResult(){
        return target.request(MediaType.APPLICATION_JSON).rx().get();
    }
    
}
