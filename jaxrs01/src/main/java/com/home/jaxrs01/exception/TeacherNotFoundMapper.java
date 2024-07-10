/**
 * 
 */
package com.home.jaxrs01.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * 
 * @author Ahmad Alrefai
 */

@Provider
public class TeacherNotFoundMapper implements ExceptionMapper<TeacherNotFoundException>{

	@Override
	public Response toResponse(TeacherNotFoundException exception) {
		System.out.println("Mapper erstellt!");
		return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
	}

}
