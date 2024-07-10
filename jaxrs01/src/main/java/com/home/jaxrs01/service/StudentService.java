/**
 * 
 */
package com.home.jaxrs01.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.home.jaxrs01.model.Student;

/**
 * 
 * @author Ahmad Alrefai
 */
public class StudentService {
	
	public List<Student> students = new ArrayList<Student>(Arrays.asList(new Student("Ahmad", 33)));
	
	public List<Student> getAllRegisterStudents(){
		Student s1 = new Student("Lise", 22);
		Student s2 = new Student("Ahmad", 23);
		Student s3 = new Student("Ali Baba", 25);
		
		List<Student> list = new ArrayList<Student>(Arrays.asList(s1, s2, s3));
		
		return list;
	}

}
