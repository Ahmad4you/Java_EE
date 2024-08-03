/**
 * 
 */
package come.home.completable_future;

import java.util.concurrent.CompletableFuture;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * http://localhost:8080/ahmad_asyncall/api/asyncFutureService
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@Path("asyncFutureService")
public class AsyncFutureService {

    @Inject
    private UserFutureBean userBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncFutureService(@Suspended AsyncResponse response) {
        CompletableFuture
                .supplyAsync(() -> userBean.getUser())
                .thenAcceptAsync((u) -> {
                    response.resume(Response.ok(u).build());
                }).exceptionally((t) -> {
                    response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(t.getMessage()).build());
                    return null;
                });
    }
}
