package model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Userslogin1
 *
 */
@Entity
@Table(name = "userslogin1")
public class Userslogin1 implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 14352344355665L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    // Getter und Setter hier
    
}
