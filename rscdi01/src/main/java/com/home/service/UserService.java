/**
 * 
 */
package com.home.service;

import java.io.Serializable;

import com.home.beans.UserBean;
import com.home.model.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



/**
 * 
 * @author Ahmad Alrefai
 */
@Path("userservice")
public class UserService implements Serializable{
    
    private static final long serialVersionUID = -8994812360564422754L;
    private User userLocal;

	@Inject
    private UserBean userBean;
    
    
    @Inject
    private void setUserLocal(){
        long ts = System.currentTimeMillis();
        userLocal = new User("Local" + ts,  "user" + ts , 34);        
    }
    /*
     * GET-Endpunkte, die JSON zurückgeben.
     */
    @GET
    @Path("getUserFromBean")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromBean(){
        return Response.ok(userBean.getUser()).build();
    }

    @GET
    @Path("getUserFromLocal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromLocal(){
        return Response.ok(userLocal).build();
    }    
    
}
