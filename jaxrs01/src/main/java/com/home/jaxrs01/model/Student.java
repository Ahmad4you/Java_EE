/**
 * 
 */
package com.home.jaxrs01.model;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Ahmad Alrefai
 */

@XmlRootElement                  // wird als XML Ausgegeben!
public class Student {          //  /ahmad_jaxrs01/resources/student
	private String name;
	private int age;

	public Student() {

	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
