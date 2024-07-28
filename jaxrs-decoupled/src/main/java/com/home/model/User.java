package com.home.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(name = "unique_full_name", columnNames = {"first_name", "last_name"})
})
public class User implements Serializable{
	

	private static final long serialVersionUID = 1L;

	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO)
     * Automatische Auswahl: Der JPA-Provider (z.B. Hibernate) wählt automatisch die am besten geeignete Strategie 
     * zur Generierung von Primärschlüsseln aus.
     * 
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @NotBlank(message = "vorname eingeben")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "nachname eingeben")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "passport_id")  // Beziehung nur in eine Richtung hier User -> Passport, umgekehrt ist unmöglich
    private Passport passport;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Zugangsdaten zugangsdaten;
    
//    @Transient
//    private transient SomeNonSerializableType someField;

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