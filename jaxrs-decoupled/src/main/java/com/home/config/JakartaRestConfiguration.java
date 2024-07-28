package com.home.config;

import java.util.HashSet;
import java.util.Set;

import com.home.service.TunWasService;
import com.home.service.UserService;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("/api")
public class JakartaRestConfiguration extends Application {
    
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(UserService.class);
        resources.add(TunWasService.class);
        return resources;
    }
}
