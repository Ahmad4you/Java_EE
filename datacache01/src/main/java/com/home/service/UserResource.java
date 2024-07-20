/**
 * 
 */
package com.home.service;

import java.util.List;

import com.home.model.User;

import datacache.UserCacheBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
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
@Stateless
public class UserResource {

    @EJB
    private UserCacheBean userCache;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userCache.get();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id) {
        User user = userCache.get().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}