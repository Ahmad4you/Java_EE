package controller;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Transient;
import model.Userslogin1;
import service.Userslogin1Service;

import java.io.Serializable;
import jakarta.annotation.PostConstruct;

//@Named("loginBean")
//@RequestScoped
@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 14354657754324567L;
	@Transient
	Userslogin1Service loginservice;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String login() throws Exception {
		loginservice = new Userslogin1Service();
		Userslogin1 user = loginservice.findByName(username);
//        if (user != null && checkPassword(user.getPassword(), password)) {
            // Benutzer gefunden und Passwort stimmt überein
		
		if (user != null) { // signing in
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);
			return "userlist.xhtml?faces-redirect=true";
		} else {
			// signing up
			System.out.println("Login() bevor");

			boolean isValid = checkCredentials(username, password);
			System.out.println("Login() after");
			if (isValid) {
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);
				
				return "userlist.xhtml?faces-redirect=true";
			} else {
				// Fehlermeldung hinzufügen
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültige Anmeldedaten", null));
				return null; // Auf derselben Seite bleiben
			}
			
		}
           
	}

	// Diese Methode würde in einer realen Anwendung das gehashte Passwort aus der
	// Datenbank abrufen
//	private String getStoredHashForUser(String username) {
//		// Hier nur ein Beispiel - ersetzen Sie dies durch einen tatsächlichen
//		// Datenbankzugriff
//		if ("admin".equals(username)) {
//			// Dies ist ein Beispiel-Hash für das Passwort "password"
//			return "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG";
//		}
//		return null;
//	}

	private boolean checkCredentials(String username, String password) {
		try {
			loginservice = new Userslogin1Service();
			if(loginservice != null) {
				
				loginservice.registerUser(username, password);
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * um mögliche Probleme bei der Initialisierung zu erkennen:
	 */

	@PostConstruct
	public void init() {
		System.out.println(
				"_______LoginBean wurde initialisiert_________________________________________________________");
	}
}
