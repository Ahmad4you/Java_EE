/**
 * 
 */
package com.home.openapi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * 
 * @author Ahmad Alrefai
 */
@Path("/")
@RequestScoped
@Tag(name = "Contact API service", description = "Methods for Contact management")
public class ContactResource {

    @GET
    @Path("/contacts")
    @APIResponse(responseCode = "200",
            description = "Contact list response",
            name = "ContactListResponse"
    )
    public Response getContacts() {
        return Response.ok("This should be a contact list").build();
    }

    @GET
    @Path("/contact/{id}")
    @APIResponse(responseCode = "200",
            description = "Single contact response",
            name = "SingleContactResponse"
    )
    @Parameter(name = "id",
            description = "Contact Id",
            required = true,
            allowEmptyValue = false
    )
    public Response getContactById(@PathParam("id") Long id) {
        return Response.ok("This is a single contact").build();
    }
}

