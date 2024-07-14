/**
 * 
 */
package com.home.hibernateCon.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "zugangsdaten")
public class Zugangsdaten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Size(min = 8, max = 255)
    @Column(name = "current_password", nullable = false)
    private String currentPassword;

    @Size(min = 8, max = 255)
    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "last_changed")
    private LocalDateTime lastChanged;

    @OneToOne
    @PrimaryKeyJoinColumn
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

    public void setEmails(String email) {
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
