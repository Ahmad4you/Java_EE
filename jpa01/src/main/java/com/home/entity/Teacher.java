package com.home.entity;

import javax.persistence.*;

@Entity
@Table(name="lehrer")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="fach")
	private String fachbereich;

	public Teacher() {
		
	}

	public Teacher(String name, String fachbereich) {
		super();
		this.name = name;
		this.fachbereich = fachbereich;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFachbereich() {
		return fachbereich;
	}

	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", fachbereich=" + fachbereich + "]";
	}

	
	
	
}
