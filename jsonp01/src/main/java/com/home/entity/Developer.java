/**
 * 
 */
package com.home.entity;

import java.util.Arrays;

/**
 * 
 * @author Ahmad Alrefai
 */
public class Developer {
	private int id;
	private String name;
	private String role;
	private String[] languages;
	
	public Developer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languges) {
		this.languages = languges;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + ", role=" + role + ", languges=" + Arrays.toString(languages)
				+ "]";
	}
	
	
	
}
