package com.home.sse;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.SseEventSource;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */
@Named
@ViewScoped
public class SseBean implements Serializable {

    private static final long serialVersionUID = 377840745090359520L;

	@NotNull
    @Positive
    private Integer countClient;
    
    private Client client;
    
    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    /*
     * Diese Methode wird aufgerufen, wenn der Button geklickt wird.
     * Sie startet einen SSE-Endpunkt auf dem Server.
     * Erstellt die angegebene Anzahl von SSE-Clients.
     * Registriert jeden Client für den Empfang von Events.
     * Wartet 10 Sekunden und schließt dann alle Verbindungen.
     * Zeigt die empfangenen Nachrichten an.
     * 
     */

    public void sendEvent() throws URISyntaxException, InterruptedException {
        WebTarget target = client.target(URI.create("http://localhost:8080/ahmad_jaxrss01/"));
        Response response = target.path("api/serverSentService/start")
                .request()
                .post(Entity.json(""), Response.class);
        
        if (response == null || response.getLocation() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to get response location"));
            return;
        }


        URI location = response.getLocation();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Sse Endpoint: " + location));
        

        final Map<Integer, String> messageMap = new ConcurrentHashMap<>(countClient);
        final SseEventSource[] sources = new SseEventSource[countClient];

        final String processUriString = target.getUri().relativize(response.getLocation()).toString(); //
        final WebTarget sseTarget = target.path(processUriString);
        
        System.out.println("Target URI: " + target.getUri());
        System.out.println("Response Location: " + response.getLocation());

        for (int i = 0; i < countClient; i++) {
            final int id = i;
            sources[id] = SseEventSource.target(sseTarget).build();
            sources[id].register((event) -> {
                final String message = event.readData(String.class);

                if (message.contains("Text")) {
                    messageMap.put(id, message);
                }
            });
            sources[i].open();
        }

        TimeUnit.SECONDS.sleep(10);

        for (SseEventSource source : sources) {
            source.close();
        }

        for (int i = 0; i < countClient; i++) {
            final String message = messageMap.get(i);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Message sent to client " + (i + 1) + ": " + message));
        }
    }

    public Integer getCountClient() {
        return countClient;
    }

    public void setCountClient(Integer countClient) {
        this.countClient = countClient;
    }

}
