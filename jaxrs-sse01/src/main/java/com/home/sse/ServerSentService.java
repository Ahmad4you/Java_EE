package com.home.sse;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.Resource;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.RequestScoped;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;


/**
 * 
 * 
 * @author Ahmad Alrefai
 */
@Path("serverSentService")
@RequestScoped
public class ServerSentService {
	private static final Logger LOGGER = Logger.getLogger(ServerSentService.class.getName());
    private static final Map<Long, UserEvent> POOL = new ConcurrentHashMap<>();

    @Resource(name = "LocalManagedExecutorService")
    private ManagedExecutorService executor;
    
    @PostConstruct
    public void init() {
        LOGGER.info("Executor service initialized: " + (executor != null));
    }
    
    /*
     * Startet einen neuen SSE-Prozess und gibt die URI zurück, unter der Clients sich registrieren können.
     */
    @Path("start")
    @POST
    public Response start(@Context Sse sse) {
    	if (executor == null) {
            return Response.serverError().entity("Executor service is not available").build();
        }

        final UserEvent process = new UserEvent(sse);

        POOL.put(process.getId(), process);
        executor.submit(process);

        final URI uri = UriBuilder.fromResource(ServerSentService.class).path("register/{id}").build(process.getId());
        return Response.created(uri).build();
    }
    
    /*
     * Registriert einen Client für einen bestimmten SSE-Prozess.
     */

    @Path("register/{id}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @GET
    public void register(@PathParam("id") Long id,
            @Context SseEventSink sseEventSink) {
        final UserEvent event = POOL.get(id);

        if (event != null) {
            event.getSseBroadcaster().register(sseEventSink);
        } else {
            throw new NotFoundException();
        }
    }
    /*
     * Eine innere Klasse, die den SSE-Prozess repräsentiert. Sie sendet nach 5 Sekunden eine Nachricht an alle registrierten Clients.
     * 
     */

    static class UserEvent implements Runnable {

        private final Long id;
        private final SseBroadcaster sseBroadcaster;
        private final Sse sse;

        UserEvent(Sse sse) {
            this.sse = sse;
            this.sseBroadcaster = sse.newBroadcaster();
            id = System.currentTimeMillis();
        }

        Long getId() {
            return id;
        }

        SseBroadcaster getSseBroadcaster() {
            return sseBroadcaster;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
                sseBroadcaster.broadcast(sse.newEventBuilder().name("register").data(String.class, "Text from event " + id).build());
                sseBroadcaster.close();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
