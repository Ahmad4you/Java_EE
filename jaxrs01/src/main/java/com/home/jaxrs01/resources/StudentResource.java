/**
 * 
 */
package com.home.jaxrs01.resources;

import java.util.List;

import com.home.jaxrs01.model.Student;
import com.home.jaxrs01.service.StudentService;

import jakarta.inject.Inject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * @author Ahmad Alrefai
 */
@Path("student")
public class StudentResource {
	
	@Inject
	StudentService studentService;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Student> getAllRegisterStudents(){
		return studentService.getAllRegisterStudents();
	}
	
	// resources/student/studentAge?name=peter&age=20
	
	@GET
	@Path("studentAge")
	@Produces(MediaType.TEXT_PLAIN)
	public String studentAge(@QueryParam("name") String name, @QueryParam("age") String age) {
		return "Der Student: " + name + "ist " + age + "Jahre Alt.";
	}
	
	// html Form -> POST
	// resources/student/studentName
	
	@Path("studentName")
	@POST
//	@Consumes("application/x-www-form-urlencoded")                                                          // gibt Format für die Eingabeparameter der Methode
	public String callStudent(@FormParam("name") String studentName) { // elemente aus Form auslesen!
		return "Unser Student heißt :" + studentName;	
	}
	
	/**
	 * CRUD Operationen
	 */
	
	//CREATE
	@POST
	@Path("createStudent")
	public String createStudent(@FormParam("name") String name, @FormParam("age") int age) {
		if(name.trim().length() > 0 && age > -1) {
			Student student = new Student(name, age);
			studentService.students.add(student);
			return "Unser Student: " + student.getName() + " wurde erfolgreich erstellt:";
		}
		return "Unser Student könnte nicht erstellt werden";
	}
	
	//READ
	@GET
	@Path("getStudent")
	public String getStudent(@QueryParam("name") String name) {
		if(name.trim().length() > 0) {
			List<Student> students = studentService.students;
			for(Student student:students) {
				if(student.getName().equals(name)) {
					return "unser Student ist enthalten: " + student.getName();
				}			
			}
		}
		return "unser Student könnte nicht gefunden werden";
	}
	
	//UPDATE
	@POST
	@Path("updateStudent")
	public String updateStudent(@FormParam("name") String name, @FormParam("age") int age) {
		if(name.trim().length() >0 && age > -1) {
			List<Student> students = studentService.students;
			for(Student student:students) {
				if(student.getName().equals(name)) {
					student.setName(name);
					student.setAge(age);
					return "unser Student ist mit dem neuen Namen enthalten: " + student.getName() + " " + student.getAge();
				}
			}
		}
		return "sorry Student ist nicht enthalten";
	}
	
	//DELETE
	@POST
	@Path("deleteStudent")
	public String deleteStudent(@FormParam("name") String name) {
		if(name.trim().length() >0 ) {
			List<Student> students = studentService.students;
			for(Student student:students) {
				if(student.getName().equals(name)) {
					studentService.students.remove(student);
					
					return "unser Student wurde erfolgreich entfernt";
				}
			}
		}
		return "sorry Student ist nicht enthalten und würde nicht gelöscht.";
	}
	
	
	
	
	
	
	

}
