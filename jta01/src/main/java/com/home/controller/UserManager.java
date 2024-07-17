/**
 * 
 */
package com.home.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.home.model.User;
import com.home.model.Zugangsdaten;
import com.home.repository.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Managed Bean fuer User
 * 
 * @author Ahmad Alrefai
 */

@Named
@RequestScoped  // @ViewScoped
public class UserManager implements Serializable{
	
private static final long serialVersionUID = -2469412437434027630L;

	//	@EJB
    @Inject
	private UserService userService;
    
	private User user = new User();
	private List<User> users;
	private Zugangsdaten zugangsdaten;
	
	@PostConstruct
	public void init() {
		loadUsers();
	    initializeUser();
	}

	public void createUser() {
		 if (user.getFirstName() == null || user.getLastName() == null 
		            || user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()) {
		            addErrorMessage("Vor- und Nachname dürfen nicht leer sein.");
		            return;
		        }
		 
        if (userService.userExists(user.getFirstName(), user.getLastName())) {
            addErrorMessage("Ein Benutzer mit diesem Namen existiert bereits.");
        } else {
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setAge(user.getAge());
            
            Zugangsdaten newZugangsdaten = new Zugangsdaten();
            newZugangsdaten.setEmail(user.getZugangsdaten().getEmail());
            newZugangsdaten.setCurrentPassword(user.getZugangsdaten().getCurrentPassword());
            newZugangsdaten.setPhoneNumber(user.getZugangsdaten().getPhoneNumber());
            newZugangsdaten.setUser(newUser);  // Setzen die Beziehung in beide Richtungen
            newUser.setZugangsdaten(newZugangsdaten);
            
            userService.create(newUser);
            addInfoMessage("Benutzer erfolgreich erstellt.");
            resetForm();
            loadUsers();
        }
    }
	
	public void updateUser() {
        if (userService.userExists(user.getFirstName(), user.getLastName()) &&
            !Objects.equals(userService.findById(user.getId()).getFirstName(), user.getFirstName()) &&
            !Objects.equals(userService.findById(user.getId()).getLastName(), user.getLastName())) {
            addErrorMessage("Ein Benutzer mit diesem Namen existiert bereits.");
        } else {
            userService.update(user.getId(), user);
            addInfoMessage("Benutzer erfolgreich aktualisiert.");
            resetForm();
            loadUsers();
        }
    }
	
	public void deleteUser(Long id) {
		userService.delete(id);
		loadUsers();
	}

	private void loadUsers() {
	    try {
	        users = userService.findAll();
	    } catch (Exception e) {
	        users = new ArrayList<>(); // or whatever default value is appropriate
	    }
	}

	private void initializeUser() {
	    if (user == null) {
	        user = new User();
	    }
	    if (user.getZugangsdaten() == null) {
	        user.setZugangsdaten(new Zugangsdaten());
	    }
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public List<User> getUsers() {
	    return users;
	}
	
	private void resetForm() {
        user = new User();
    }
	
	private void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    private void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
    }
    
    public Zugangsdaten getZugangsdaten() {
        return zugangsdaten;
    }

    public void setZugangsdaten(Zugangsdaten zugangsdaten) {
        this.zugangsdaten = zugangsdaten;
    }

}
