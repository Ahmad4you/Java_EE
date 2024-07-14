package com.home.hibernateCon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emailaddress")
public class emailAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emailAddressId")
	private Integer emailAddressId;
	
	private String address;

	public emailAddress() {
	}
	
	public emailAddress(String address) {
		super();
		this.address = address;
	}

	public Integer getEmailAddressId() {
		return emailAddressId;
	}

	public void setEmailAddressId(Integer emailAddressId) {
		this.emailAddressId = emailAddressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
