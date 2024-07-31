/**
 * 
 */
package com.home.microprofile;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.helidon.security.SecurityContext;
import io.helidon.webserver.*;

/**
 * 
 * @author Ahmad Alrefai
 */
//@RoutingPath("/reactive")
@ApplicationScoped
public class ReactiveService implements Service {
    @Inject
    private SecurityContext securityContext;
    
    /*
     * Diese Methode definiert eine Regel, um auf HTTP GET-Anfragen zu reagieren und den SecurityContext als Antwort zu senden.
     */

//    @Override
//    public void update(Routing.Rules rules) {
//        rules.get("/", (req, res) -> res.send("Context: " + securityContext));
//    }
    
    @Override
    public void update(Routing.Rules rules) {
        rules.get("/reactive", this::handleGet);
    }

    private void handleGet(ServerRequest req, ServerResponse res) {
        res.send("Context: " + securityContext);
    }
}

