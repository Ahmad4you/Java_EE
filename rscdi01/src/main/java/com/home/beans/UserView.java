/**
 * 
 */
package com.home.beans;

import java.io.Serializable;
import java.util.List;

import com.home.datacache.UserCacheBean;
import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;


/**
 * 
 * @author Ahmad Alrefai
 */
@ViewScoped
@Named
public class UserView implements Serializable {

    private static final long serialVersionUID = 1788941319502283402L;
    private List<User> users;
    private Client client;
    private WebTarget target;
    
    @EJB
    private UserCacheBean userCache;
    
    /*
     * loadUsers() erstellt einen JAX-RS-Client, um HTTP-Anfragen zu senden.
     * Diese Codezeilen senden eine GET-Anfrage an den "getUserFromBean"-Endpunkt und fügen eine Nachricht zum FacesContext hinzu.
     * 
     */
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/ahmad_rscdi01/api");
//        users = userCache.get();
    }

	public void loadUsers() {
		try {
        User response = target.path("userservice/getUserFromBean")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(User.class);
        
        
//        List<User> response = target.path("userservice/getUsers")
//                .request()
//                .accept(MediaType.APPLICATION_JSON)
//                .get(new GenericType<List<User>>(){});

        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage("userFromBean: " + response));

        response = target.path("userservice/getUserFromLocal")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(User.class);

        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage("userFromLocal: " + response));
        
        users = userCache.get();
//      users = target.path("/users")
//              .request(MediaType.APPLICATION_JSON)
//              .get(new GenericType<List<User>>(){});
        
	 } catch (WebApplicationException e) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error loading users", e.getMessage()));
	        e.printStackTrace(); 
	    } finally {
	        client.close();
	    }
	}
	
	public List<User> getUsers() {
        return users;
    }
	
	 public int getUserCount() {
	        return users.size();
	    }

//    public void loadUsers() {
//        // Hier lade die Benutzer, z.B. aus einer Datenbank
//        users = userService.getAllUsers();
//    }

}
