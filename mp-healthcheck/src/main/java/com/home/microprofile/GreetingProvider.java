/**
 * 
 */
package com.home.microprofile;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GreetingProvider {
    private final AtomicReference<String> message = new AtomicReference<>();

    /*
     * Diese Klasse bietet eine Möglichkeit, eine Begrüßungsnachricht zu verwalten
     */
    @Inject
    public GreetingProvider(@ConfigProperty(name = "app.greeting") String message) {
        this.message.set(message);
    }

    String getMessage() {
        return message.get();
    }

    void setMessage(String message) {
        this.message.set(message);
    }
    
 // No-args constructor for proxying
    public GreetingProvider() {
        this.message.set("Hello");
    }
}
