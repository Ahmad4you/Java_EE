/**
 * 
 */
package com.home.jaxrs01.exception;

/**
 * 
 * @author Ahmad Alrefai
 */
public class TeacherNotFoundException extends Exception{
	
	public TeacherNotFoundException() {
		super();
	}
	
	public TeacherNotFoundException(String message) {
		super(message);
		System.out.println("TeacherNotFoundException geworfen!");
	}

}
