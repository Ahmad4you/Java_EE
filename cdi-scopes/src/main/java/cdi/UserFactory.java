package cdi;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 * Wenn man die User-Klasse manuell erstellen möchten, anstatt sie zu injizieren, können einen Producer verwendet.
 * 
 * @author Ahmad Alrefai
 */
@ApplicationScoped
public class UserFactory implements Serializable {

    private static final long serialVersionUID = 2386751724245824425L;

    @GET
    @Path("getUser")
    @Produces
    public User getUser() {
    	User user = new User();
    	user.setName("Ahmad Alrefai");
    	user.setEmail("ahmad@outlook.de");
        return user;
    }

}
