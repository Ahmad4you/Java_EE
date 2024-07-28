/**
 * 
 */
package com.home.gateway;


import com.home.model.UserAddress;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

/**
 * 
 * In einer Microservices-Architektur ist es üblich, dass verschiedene Dienste unabhängig voneinander entwickelt und bereitgestellt werden, 
 * aber dennoch miteinander kommunizieren müssen. neues Projekt (GatewayResource) scheint ausgelegt zu sein, Daten von einem alten anderen Service 
 * (UserService und UserAddressService) zu konsumieren. Um diese Interaktion zu testen und sicherzustellen, dass alles korrekt funktioniert, 
 * soll beide Projekte gleichzeitig laufen.
 * 
 * Echte Bedingungen simulieren: Durch das gleichzeitige Deployment beider Projekte
 * In modernen CI/CD-Pipelines werden Services oft parallel gestartet, um Tests durchzuführen und sicherzustellen, 
 * dass neue Änderungen keine bestehenden Funktionalitäten beeinträchtigen. Wenn beide Projekte gleichzeitig laufen,
 * 
 * 
 * @author Ahmad Alrefai
 */

@Consumes(MediaType.APPLICATION_JSON)
@Path("gatewayResource")
@RequestScoped
public class GatewayResource {

    private final String hostURI = "http://localhost:8080/";
    private Client client;
    private WebTarget targetUser;
    private WebTarget targetAddress;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        targetUser = client.target(hostURI + "ahmad_address/api/userService/"); // micro_x_mono_address Projekt
        targetAddress = client.target(hostURI + "ahmad_address/api/userAddressService/");  // micro_x_mono_address Projekt
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }

    @GET
    @Path("getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        WebTarget service = targetUser.path("get");

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }
        
        if (response.getStatus() == 404) {
  
            System.out.println("URL not found: " + service.getUri().toString());
        }

        GatewayResponse gatewayResponse = new GatewayResponse();
        gatewayResponse.setResponse(response.readEntity(String.class));
        gatewayResponse.setFrom(targetUser.getUri().toString());

        return Response.ok(gatewayResponse).build();
    }
    
    @GET
    @Path("getUserAddresses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAddresses() {
        WebTarget service = targetAddress.path("get");

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }
        
        if (response.getStatus() == 404) {
  
            System.out.println("URL not found: " + service.getUri().toString());
        }

        GatewayResponse gatewayResponse = new GatewayResponse();
        gatewayResponse.setResponse(response.readEntity(String.class));
        gatewayResponse.setFrom(targetAddress.getUri().toString());

        return Response.ok(gatewayResponse).build();
    }
    
    /**
     * curl -X POST http://localhost:8080/ahmad_gateway/api/gatewayResource/addAddress 
     * -H "Content-Type: application/json" 
     * -d "{\"idUser\": 4, \"street\": \"Neustraße\", \"number\": \"444\", \"city\": \"Stuttgart\", \"zip\": \"98743\", \"state\": \"Musterland\",
     *  \"country\": \"Musterland\"}"
     *  
     *  
     * @param address
     * @return
     */

    @POST
    @Path("addAddress")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response addAddress(UserAddress address) {
        WebTarget service = targetAddress.path("add");

        Response response;
        try {
            response = service.request().post(Entity.json(address));
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        return Response.fromResponse(response).build();
    }

}
