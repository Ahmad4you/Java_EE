/**
 * 
 */
package com.home.hibernateCon.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Ahmad Alrefai
 */
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "bezeichnung", nullable = false)
    private String bezeichnug;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<Student>();
    
    public Course() {
    	
    }

	public Course(String bezeichnug, Set<Student> students) {
		super();
		this.bezeichnug = bezeichnug;
		this.students = students;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnug;
	}

	public void setBezeichnung(String bezeichnug) {
		this.bezeichnug = bezeichnug;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

    
}