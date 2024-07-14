package com.home.hibernateCon.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gamer")
public class Gamer {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gid")
	private Long id;
	
	private String name;
	
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fK_gamer_id")
	private Set<emailAddress> addresses = new HashSet<emailAddress>();
	
	public Gamer() {
		
	}

	public Gamer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<emailAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<emailAddress> addresses) {
		this.addresses = addresses;
	}
	
	
	
}
