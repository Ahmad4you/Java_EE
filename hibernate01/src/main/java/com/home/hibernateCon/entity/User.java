package com.home.hibernateCon.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(name = "unique_full_name", columnNames = {"first_name", "last_name"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "passport_id")  // Beziehung nur in eine Richtung hier User -> Passport, umgekehrt ist unmöglich
    private Passport passport;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Zugangsdaten zugangsdaten;

    public User() {
    }

    public User(String firstName, String lastName, int age, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passport = passport;
    }
    
    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Getter und Setter Methoden

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Zugangsdaten getZugangsdaten() {
        return zugangsdaten;
    }

    public void setZugangsdaten(Zugangsdaten zugangsdaten) {
        this.zugangsdaten = zugangsdaten;
        if (zugangsdaten != null) {
            zugangsdaten.setUser(this);
        }
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", passport="
				+ passport + ", zugangsdaten=" + zugangsdaten + "]";
	}
}