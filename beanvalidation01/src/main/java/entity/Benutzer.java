package entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */

@Entity
@Table(name = "userslogin1")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Benutzer implements Serializable{

    private static final long serialVersionUID = 3473348777967621971L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull(message = "Name darf nicht null sein")
    @Size(min = 2, max = 30, message = "Name muss zwischen 2 und 30 Zeichen lang sein")
    @Column(name = "username", nullable = false, unique = true)
//    @JsonProperty("name")
    private String name;

    @NotNull(message = "E-Mail darf nicht null sein")
    @Email(message = "E-Mail muss gültig sein")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

//    @Min(value = 18, message = "Alter muss mindestens 18 sein")
//    @Max(value = 150, message = "Alter muss maximal 150 sein")
//    @Column(name = "alter")
//    private int age;
    
    @NotNull(message = "Passwort darf nicht null sein")
    @Size(min = 8, message = "Passwort muss mindestens 8 Zeichen lang sein")
    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
