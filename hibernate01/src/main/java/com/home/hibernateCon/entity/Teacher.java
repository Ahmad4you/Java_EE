package com.home.hibernateCon.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TID")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="fach")
	private String fachbereich;
	
	@OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
	private TeacherAddresse teacherAddresse;

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

	public TeacherAddresse getTeacherAddresse() {
		return teacherAddresse;
	}

	public void setTeacherAddresse(TeacherAddresse teacherAddresse) {
		this.teacherAddresse = teacherAddresse;
	}
	
	
}
