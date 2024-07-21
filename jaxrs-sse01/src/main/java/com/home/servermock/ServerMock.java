/**
 * 
 */
package com.home.servermock;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * Diese Klasse startet einen Mock-Server mit Grizzly.
 * 
 * @author Ahmad Alrefai
 */
public class ServerMock {

    public static final URI CONTEXT = URI.create("http://localhost:8080/");
    public static final String BASE_PATH = "ssevents";

    public static void main(String[] args) {
        try {
            final ResourceConfig resourceConfig = new ResourceConfig(SseResource.class);

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(CONTEXT, resourceConfig, false);
            server.start();

            System.out.println(String.format("Mock Server started at %s%s", CONTEXT, BASE_PATH));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
