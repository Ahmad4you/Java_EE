/**
 * 
 */
package come.home.task_status;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.concurrent.ManagedTaskListener;

import com.home.model.User;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class AsyncStatustask implements Callable<User>, ManagedTaskListener {

    private final long instantiationMili = new Date().getTime();
    
    private static final Logger LOG = Logger.getAnonymousLogger();
    
    @Override
    public User call() throws Exception {
        return new UserTaskstatusBean().getUser();
    }

    @Override
    public void taskSubmitted(Future<?> future, ManagedExecutorService mes, Object o) {
        long mili = new Date().getTime();
        LOG.log(Level.INFO, "taskSubmitted: {0} - Miliseconds since instantiation: {1}", new Object[]{future, mili - instantiationMili});
    }

    @Override
    public void taskAborted(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        long mili = new Date().getTime();
        LOG.log(Level.INFO, "taskAborted: {0} - Miliseconds since instantiation: {1}", new Object[]{future, mili - instantiationMili});
    }

    @Override
    public void taskDone(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        long mili = new Date().getTime();
        LOG.log(Level.INFO, "taskDone: {0} - Miliseconds since instantiation: {1}", new Object[]{future, mili - instantiationMili});
    }

    @Override
    public void taskStarting(Future<?> future, ManagedExecutorService mes, Object o) {
        long mili = new Date().getTime();
        LOG.log(Level.INFO, "taskStarting: {0} - Miliseconds since instantiation: {1}", new Object[]{future, mili - instantiationMili});
    }

}
