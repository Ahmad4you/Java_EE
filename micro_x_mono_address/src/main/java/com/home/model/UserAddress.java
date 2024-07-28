/**
 * 
 */
package com.home.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 
 * @author Ahmad Alrefai
 */
@Entity
@Table(name = "useraddress")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long user_id;
    
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String street;
    
    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String number;
    
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String city;
    
    @NotBlank
    @Pattern(regexp = "\\d{5}", message = "Zip code must be 5 digits")
    @Column(nullable = false)
    private String zip;
    
    @Size(max = 50)
    private String state;
    
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String country;

    
    public UserAddress() {}

    public UserAddress(Long idUser, String street, String number, String city, String zip, String state, String country) {
        this.user_id = idUser;
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return user_id;
    }

    public void setIdUser(Long idUser) {
        this.user_id = idUser;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
