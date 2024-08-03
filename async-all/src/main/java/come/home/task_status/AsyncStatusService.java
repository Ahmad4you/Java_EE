/**
 * 
 */
package come.home.task_status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedExecutorService;
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
@Path("asyncStatusService")
public class AsyncStatusService {

    @Resource
    private ManagedExecutorService executor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncStatusService(@Suspended AsyncResponse response) {
        int i = 0;

        List<User> usersFound = new ArrayList<>();
        while (i < 4) {
            Future<User> result = executor.submit(new AsyncStatustask());

            while (!result.isDone()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            try {
                usersFound.add(result.get());
            } catch (InterruptedException | ExecutionException ex) {
                System.err.println(ex.getMessage());
            }

            i++;
        }

        response.resume(Response.ok(usersFound).build());
    }

}

