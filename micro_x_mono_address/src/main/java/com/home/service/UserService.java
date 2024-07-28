package com.home.service;

import com.home.bean.UserBeanMock;
import com.home.model.User;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */
@Path("userService")
public class UserService {
    
    @EJB
    private UserBeanMock userBean;
    
    @GET
    @Path("findById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        return Response.ok(userBean.findById(id)).build();
    }
    
    @GET
    @Path("get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        return Response.ok(userBean.get()).build();
    }    
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response add(User user){
        userBean.add(user);
        return Response.accepted().build();
    }    
    
    @DELETE
    @Path("remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response remove(@PathParam("id") Long id){
        userBean.remove(userBean.findById(id));
        return Response.accepted().build();
    }
}
