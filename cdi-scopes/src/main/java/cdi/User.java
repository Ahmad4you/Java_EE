package cdi;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Ahmad Alrefai
 */

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -3746112524052849988L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public User(String name, String email) {
//		this.name = name;
//		this.email = email;
//	}

	@Override
	public String toString() {
		return "User{" + "name=" + name + ", email=" + email + '}';
	}

}
