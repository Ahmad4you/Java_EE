/**
 * 
 */
package com.home.jaxrs01.resources;

import com.home.jaxrs01.exception.TeacherNotFoundException;
import com.home.jaxrs01.model.Teacher;

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

/**
 * 
 * Exception Mapping -> Exception Mapper
 * Exception standardmäßig vom Container geschmissen
 * ich werde mappen eine geworfene application in eine Response Objekt
 * 
 * @author Ahmad Alrefai
 */

@Path("/teacher")                        // resources/teacher
public class TeacherResource {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getTeacherName() throws TeacherNotFoundException {
		Teacher teacherDB= null;
		if(teacherDB == null) {
//			throw new IllegalStateException("Teacher not found");
			throw new TeacherNotFoundException("Teacher not found");
		}
		return teacherDB.getName();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacher(@PathParam("id") int id) throws TeacherNotFoundException {
	    Teacher teacher = findTeacherById(id);
	    if (teacher == null) {
	        throw new TeacherNotFoundException("Teacher with id " + id + " not found");
	    }
	    return Response.ok(teacher).build();
	}

	private Teacher findTeacherById(int id) {
	    // Hier soll normalerweise eine Datenbankabfrage durchführen
	    // hier wird einen Dummy-Lehrer zurück gegeben
	    if (id == 1) {
	        return new Teacher("John Doe", 35);
	    }
	    return null;
	}
	
	
	@GET
    @Path("/testresponse")
	public Response testClient() {
		return Response.ok().build();
	}
	

}
