package cdi;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;

/**
 * http://localhost:8080/ahmad_cdi_one/ahmad/userservice/getUser
 * 
 * @author Ahmad Alrefai
 */
@ApplicationPath("ahmad")
public class Application extends jakarta.ws.rs.core.Application {
	 @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<>();
	        classes.add(UserService.class);
	        return classes;
	    }
}
