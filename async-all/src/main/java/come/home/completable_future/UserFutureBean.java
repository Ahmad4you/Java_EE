/**
 * 
 */
package come.home.completable_future;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.ejb.Stateless;



/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserFutureBean {

    public User getUser() {
        long id = new Date().getTime();
        try {
            TimeUnit.SECONDS.sleep(5);
            return new User("User " + id, "Nachname " + id, 22);
        } catch (InterruptedException ex) {
        	System.err.println(ex.getMessage());
            return new User("Error " + id, "InterruptedException", 1);
        }
    }

}