/**
 * 
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.UserService;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private User user;
    private User selectedUser;
    private List<User> users;
    private String username;
    private String password;
    private String email;
    private Date birthDate;
    private String address;
    
    @Inject
    private UserService userService;
    
    @PostConstruct
    public void init(){
        user = new User();
        user.setName("Ahmad Meister");
        user.setEmail("ahmad@meister.de");
        loadUsers();
    }
    
    public UserBean(){
    	 System.out.println("UserBean wurde erstellt");
    }

    private void loadUsers() {
        // Hier würden normalerweise der Benutzer aus einer Datenbank laden
    	 users = userService.findAllUsersJPQL();
    	    if (users == null || users.isEmpty()) {
    	        users = new ArrayList<>();
    	        User user1 = new User();
    	        user1.setName("Max Mustermann");
    	        user1.setEmail("musterman@hotmail.de");
    	        users.add(user1);
    	    }
    }
    /*
     * Der erste Parameter null bedeutet, dass die Nachricht nicht mit einer bestimmten Komponente auf der Seite verknüpft ist. 
     * Es ist eine globale Nachricht.
     * <h:messages globalOnly="true" />
     */

    public void userAction(){
    	 FacesContext context = FacesContext.getCurrentInstance();
    	 context.getMessageList().clear(); // to Löschen vorherige Nachrichten
    	 if (user != null && selectedUser != null) {
    	        context.addMessage(null, 
    	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Benutzer validiert: " + user.getName()));
    	        
    	        // Hier kann man weitere Aktionen durchführen, z.B. den Benutzer speichern
    	        // userService.saveUser(user);
    	    } else {
    	        context.addMessage(null, 
    	            new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung", "Bitte wählen Sie einen Benutzer aus"));
    	    }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    // Methode zum Aktualisieren des ausgewählten Benutzers
    public void onUserSelect() {
        if (selectedUser != null) {
            user = selectedUser;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausgewählter Benutzer: " + selectedUser.getName()));
        }
        // to Löschen alle vorherigen Fehlermeldungen
//        FacesContext.getCurrentInstance().getMessageList().clear();
    }

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * dynamische Validierung implementieren
	 * Feedback geben, während der Benutzer tippt. Dafür verwendet wird die Ajax-Funktionalität von PrimeFaces.
	 * 
	 * <p:inputText id="username" value="#{userBean.username}">
	 * <p:ajax event="keyup" listener="#{userBean.validateUsername}" update="usernameMessage" />
	 */
    
	public void validateUsername() {
        if (username != null && username.length() < 4) {
            FacesContext.getCurrentInstance().addMessage("username", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Ungültiger Benutzername", "Der Benutzername muss mindestens 4 Zeichen lang sein."));
        }
    }

    public void validateEmail() {
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            FacesContext.getCurrentInstance().addMessage("email", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Ungültige E-Mail", "Bitte geben Sie eine gültige E-Mail-Adresse ein."));
        }
    }

    public void validatePassword() {
        if (password != null && password.length() < 8) {
            FacesContext.getCurrentInstance().addMessage("password", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Unsicheres Passwort", "Das Passwort muss mindestens 8 Zeichen lang sein."));
        }
    }
    
    /**
     * <p:commandButton value="Registrieren" action="#{userBean.register}" update="@form" style="margin-top: 10px;" />
     */

    public void register() {
        // Implementieren hier die Registrierungslogik
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Registrierung erfolgreich", "Willkommen, " + username + "!"));
    }
    
    
    
}