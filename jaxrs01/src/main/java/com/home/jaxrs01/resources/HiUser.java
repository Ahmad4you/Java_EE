/**
 * 
 */
package com.home.jaxrs01.resources;

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
@Path("hiuser")
public class HiUser {
	
	@GET
	@Path("{user}")                     // ahmad_jaxrs01/resources/hiuser/diana
	@Produces(MediaType.TEXT_PLAIN)   // MIME Typ angeben für Antwort
	public Response getUserName(@PathParam("user") String name) { // zurückgabe Antwort
		String username = "Hallo " + name;
		return Response.status(Response.Status.OK).entity(username).build(); // entity: inhalt der body im Antwort, Build: Antwort erzeugung
	}
	
	

}
