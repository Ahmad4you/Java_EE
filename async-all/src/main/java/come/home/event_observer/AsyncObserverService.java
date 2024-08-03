/**
 * 
 */
package come.home.event_observer;

import java.util.Date;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@Path("asyncObserverService")
public class AsyncObserverService {
    
    @Inject
    private Event<User> event;
    
    private AsyncResponse response;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncObserverService(@Suspended AsyncResponse response){
        long id  = new Date().getTime();
        this.response = response;
        event.fireAsync(new User("User " + id, "Nachname " + id, 22));
    }
    
    public void onFireEvent(@ObservesAsync User user){
        response.resume(Response.ok(user).build());
    }
}
