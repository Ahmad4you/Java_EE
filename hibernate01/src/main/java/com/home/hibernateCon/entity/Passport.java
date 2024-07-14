package com.home.hibernateCon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false, updatable = false)
    private Long id;
    
    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "passport_no", unique = true)
    private String passportNo;
    
    
    @Column(name = "issue_date")
    private LocalDate issueDate;
    
    
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    
    @Column(name = "country_of_issue", length = 50)
    private String countryOfIssue;
    

    // Default constructor
    public Passport() {}

    // Constructor with fields
    public Passport(String passportNo, LocalDate issueDate, LocalDate expiryDate, String countryOfIssue) {
        this.passportNo = passportNo;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.countryOfIssue = countryOfIssue;
    }
    
    public Passport(String passportNo) {
        this.passportNo = passportNo;
        
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    // Useful method to check if the passport is valid
    public boolean isValid() {
        return LocalDate.now().isBefore(expiryDate);
    }

    // toString method for easy logging and debugging
    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passportNo='" + passportNo + '\'' +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", countryOfIssue='" + countryOfIssue + '\'' +
                '}';
    }
}