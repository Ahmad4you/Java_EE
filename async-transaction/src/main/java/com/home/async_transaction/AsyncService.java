package com.home.async_transaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Ausführung langer Operationen ohne Blockieren des Hauptthreads 
 * und zur asynchronen Rückgabe von Ergebnissen an den Client.
 * 
 * http://localhost:8080/ahmad_asynctransaction/api/asyncService
 * 
 * 
 * @author Ahmad Alrefai
 */
@Path("asyncService")
@RequestScoped
public class AsyncService {
    
    private AsyncTask asyncTask;
    
    @Resource(name = "LocalManagedExecutorService")
    private ManagedExecutorService executor;   
    
    @PostConstruct
    public void init(){
        asyncTask = new AsyncTask();
    }
    
    /*
     * Diese Methode wird aufgerufen, wenn eine GET-Anfrage an den Endpunkt asyncService gestellt wird.
     * Reicht das AsyncTask-Objekt an den executor ein.
     * Wartet, bis die Aufgabe abgeschlossen ist, indem sie periodisch (jede Sekunde) überprüft, ob das Ergebnis verfügbar ist.
     * 
     */
    		
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncService(@Suspended AsyncResponse response){
        
        Future<User> result = executor.submit(asyncTask);
        
        while(!result.isDone()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        try {
            response.resume(Response.ok(result.get()).build());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex.getMessage());
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build());
        }
        
    }
}
