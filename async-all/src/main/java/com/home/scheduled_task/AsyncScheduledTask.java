/**
 * 
 */
package com.home.scheduled_task;

import java.util.concurrent.Callable;

import com.home.model.User;

import jakarta.enterprise.inject.spi.CDI;


/**
 * 
 * @author Ahmad Alrefai
 */
public class AsyncScheduledTask implements Callable<User> {
    
    private final UserScheduledBean userScheduledBean = CDI.current().select(UserScheduledBean.class).get();

    @Override
    public User call() throws Exception {
        return userScheduledBean.getUser();
    }

}
