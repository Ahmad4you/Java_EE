/**
 * 
 */
package com.home.controller;

import java.util.List;
import java.util.logging.Logger;

import com.home.model.User;
import com.home.repository.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * 
 * @author Ahmad Alrefai
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	Logger logger = Logger.getLogger(UserController.class.getName());
	
    @Inject
    private UserService userService;

    @POST
    public Response addUser(User user) {
        userService.create(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
//        user.setId(id);
        userService.update(id, user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeUser(@PathParam("id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(user.getId());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    public Response getAllUsers() {
        logger.info("Entering getAllUsers method");
        try {
            List<User> users = userService.findAll();
            logger.log(null, "Found {} users", users.size());
            return Response.ok(users).build();
        } catch (Exception e) {
            logger.log(null, "Error retrieving users", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error retrieving users: " + e.getMessage())
                           .build();
        }
    }
    
    /*
     * http://localhost:8080/ahmad_arquillian/api/users/test
     */
    @GET
    @Path("/test")
    public Response testEndpoint() {
        return Response.ok("Test successful").build();
    }
}
