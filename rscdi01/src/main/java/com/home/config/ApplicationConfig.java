package com.home.config;

import java.util.HashSet;
import java.util.Set;

import com.home.service.UserService;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * 
 * UserService Klasse mit @Provider annotieren
 * In diesem Fall soll die getClasses() Methode in ApplicationConfig nicht überschreiben.
 * @author Ahmad Alrefai
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * 
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UserService.class);
    }
    
//    @WebListener
//    public class RestApplicationConfig implements ServletContextListener {
//        @Override
//        public void contextInitialized(ServletContextEvent sce) {
//            ServletContext sc = sce.getServletContext();
//            sc.setAttribute("javax.ws.rs.Application", new Application() {
//                @Override
//                public Set<Class<?>> getClasses() {
//                    Set<Class<?>> classes = new HashSet<>();
//                    classes.add(UserService.class);
//                    // Fügen hier weitere Klassen hinzu
//                    return classes;
//                }
//            });
//        }
//    }
//    
}
