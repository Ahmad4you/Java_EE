/**
 * 
 */
package com.home.hibernateCon.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "zugangsdaten")
public class Zugangsdaten implements Serializable{

    private static final long serialVersionUID = 6358626293805510019L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @NotBlank
    @NotNull(message = "Die E-Mail-Adresse darf nicht leer sein")
    @Email(message = "Bitte geben Sie eine gültige E-Mail-Adresse ein")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @NotNull(message = "Das aktuelle Passwort darf nicht leer sein")
    @Size(min = 8, max = 255, message = "Das Passwort muss zwischen 8 und 255 Zeichen lang sein")
    @Column(name = "current_password", nullable = false)
    private String currentPassword;

    @Size(min = 8, max = 255, message = "Das alte Passwort muss zwischen 8 und 255 Zeichen lang sein")
    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "last_changed")
    private LocalDateTime lastChanged;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Zugangsdaten() {
    }

    public Zugangsdaten(String email,String currentPassword) {
    	this.email = email;
        this.currentPassword = currentPassword;
        this.lastChanged = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.oldPassword = this.currentPassword;
        this.currentPassword = currentPassword;
        this.lastChanged = LocalDateTime.now();
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public LocalDateTime getLastChanged() {
        return lastChanged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Method to check if the password needs to be changed (e.g., older than 360 days)
    public boolean needsChange() {
        return LocalDateTime.now().minusDays(360).isAfter(lastChanged);
    }
}
