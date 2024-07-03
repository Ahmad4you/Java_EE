/**
 * 
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * @author Ahmad Alrefai
 */

@Entity
@Table(name = "userslogin1")
public class User implements Serializable {
	
	private static final long serialVersionUID = -5751549718405027969L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String username;
	@Column
    private String email;

//    public User(String name, String email) {
//        this.username = name;
//        this.email = email;        
//    }   
	
	public User() {
		
	}

	public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }      
    
    public Integer getId() {
		return id;
	}

    @Override
    public String toString() {
        return "User{name='" + username + "', email='" + email + "'}";
    }


}
