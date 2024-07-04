package cdi;

import cdi.profile.Profile;
import cdi.profile.ProfileType;
import cdi.profile.UserProfile;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * dies ist ein RESTful Web Service, der verschiedene Benutzerprofile verwaltet und ein Ereignissystem verwendet, 
 * um Benachrichtigungen zu senden.
 * 
 * @Profile Annotation wird verwendet, um verschiedene UserProfile Implementierungen zu injizieren.
 * @author Ahmad Alrefai
 */
@Path("userservice")
@RequestScoped
public class UserService {
    
    @Inject
    private User user;
    
    @Inject
    @Profile(ProfileType.ADMIN)
    private UserProfile userProfileAdmin;
    
    @Inject
    @Profile(ProfileType.OPERATOR)
    private UserProfile userProfileOperator;
    
    @Inject
    @Profile(ProfileType.DEFAULT)
    private UserProfile userProfileDefault;
    
    @Inject
    @Profile(ProfileType.DATENSCHUTZ)
    private UserProfile userProfileDatenschutz;
    
    @Inject
    private Event<User> userEvent;
    
    @GET
    @Path("getUser")
    @Produces(MediaType.TEXT_HTML)
    public Response getUser() {
        
    	System.out.println("getUser method called");
//        request.setAttribute("result", user);
//        request.getRequestDispatcher("/result.jsp").forward(request, response);
    	 String htmlContent = "<html><body><h1>Benutzerdaten</h1><p>Hier sind die Benutzerdaten...</p></body></html>";
        return Response.ok(htmlContent).build();
    }    
    
    @GET
    @Path("getProfileAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileAdmin() {
    	
    	ProfileType type = fireUserEvents(userProfileAdmin.type());
        return Response.ok(type).build();
    }
    
    @GET
    @Path("getProfileOperator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileOperator() {
        
    	ProfileType type = fireUserEvents(userProfileOperator.type());
        return Response.ok(type).build();
    }

    @GET
    @Path("getProfileDefault")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileDefault() {
        
    	ProfileType type = fireUserEvents(userProfileDefault.type());
        return Response.ok(type).build();
    } 
    
    @GET
    @Path("getProfileDatenschutz")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileDatenschutz() {
        
    	ProfileType type = fireUserEvents(userProfileDatenschutz.type());
        return Response.ok(type).build();
    }    
    
    private ProfileType fireUserEvents(ProfileType type){
        userEvent.fire(user);
        userEvent.fireAsync(user);
        return type;
    }
    
    public void sendUserNotification(@Observes User user){
        System.out.println("sendUserNotification: " + user);
    }
    
    public void sendUserNotificationAsync(@ObservesAsync User user){
        System.out.println("sendUserNotificationAsync: " + user);
    }    
    
}
