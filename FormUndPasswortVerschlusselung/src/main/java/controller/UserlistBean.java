package controller;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Userslogin1;
import service.Userslogin1Service;

@Named
@RequestScoped
public class UserlistBean {

	public UserlistBean() {
	}

	@Inject
	private Userslogin1Service userlistRepository;

	private List<Userslogin1> users;
	private String activuser;

	public void init() throws Exception {
		System.out.println("calling Data by init();;;;;;;;;;;;;;;;;;;;");
//		loadUsers();
	}

	@PostConstruct
	public void loadUsers() throws Exception {
		try {
			users = userlistRepository.getAllUsers();
			System.out.println("Anzahl der geladenen Benutzer: " + users.size());
		} catch (Exception e) {
			System.err.println("Fehler beim Laden der Benutzer: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Fehler beim Laden der Benutzer: " + e.getMessage(), null));
		}
	}

	public List<Userslogin1> getUsers() {
		return users;
	}

	public Userslogin1 getLoggedInUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (Userslogin1) context.getExternalContext().getSessionMap().get("loggedInUser");
	}

	public String getLoggedInUsername() {
		Userslogin1 loggedInUser = getLoggedInUser();
		return (loggedInUser != null) ? loggedInUser.getUsername() : "Not logged in";
	}
	
	public String getAktivuser() {
	    try {
	        activuser = getLoggedInUsername();
	        return activuser;
	    } catch (Exception e) {
	       
	        return "Unknown User";
	    }
	}

	/*
	 * Diese Methode invalidiert die gesamte Session, was effektiv alle
	 * gespeicherten Daten, einschließlich des angemeldeten Benutzers, entfernt.
	 * 
	 */

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}

}
