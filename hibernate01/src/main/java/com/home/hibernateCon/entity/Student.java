/**
 * 
 */
package com.home.hibernateCon.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


/**
 * 
 * @author Ahmad Alrefai
 */
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    
    @Column(name = "nachname", nullable = false)
    private String nachname;
    
    @Column(name = "vorname", nullable = false)
    private String vorname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<Course>();
    
    public Student() {
    	
    }
    
    
	public Student(String nachname, String vorname, Set<Course> courses) {
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

    
}
