/**
 * 
 */
package com.home.service;

import com.home.bean.UserAddressBean;
import com.home.model.UserAddress;

import jakarta.ejb.EJB;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

/**
 * 
 * @author Ahmad Alrefai
 */


@Path("userAddressService")
public class UserAddressService {
    
//    @EJB
//    private UserAddressBeanMock userAddressBean;
    
    @EJB
    private UserAddressBean userAddressBean;
    
    private final Jsonb jsonbBuilder = JsonbBuilder.create();
    
    /**
     * curl -X GET http://localhost:8080/ahmad_address/api/userAddressService/findById/3 
     * -H "Content-Type: application/json"
     * 
     * @param id
     * @return
     */
    @GET
    @Path("findById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        return Response.ok(jsonbBuilder.toJson(userAddressBean.findById(id))).build();
    }
    
    /*
     * http://localhost:8080/ahmad_address/api/userAddressService/get
     * 
     */
    
    @GET
    @Path("get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        return Response.ok(jsonbBuilder.toJson(userAddressBean.get())).build();
    }  
    
    /**
     * 
     * 
     * curl -X POST http://localhost:8080/ahmad_address/api/userAddressService/add 
     * -H "Content-Type: application/json" 
     * -d "{\"idUser\": 2, \"street\": \"Musterstraße2\", \"number\": \"222\", \"city\": \"Musterstadt\", \"zip\": \"12345\", 
     * \"state\": \"Musterland\", \"country\": \"Musterland\"}"
     * 
     * @param address
     * @return
     */
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response add(UserAddress address){
        userAddressBean.add(address);
        return Response.accepted().build();
    }    
    
    @DELETE
    @Path("remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response remove(@PathParam("id") Long id){
        userAddressBean.remove(userAddressBean.findById(id));
        return Response.accepted().build();
    }
    
}
