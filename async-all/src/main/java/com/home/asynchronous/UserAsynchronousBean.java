/**
 * 
 */
package com.home.asynchronous;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class UserAsynchronousBean {
    
    @Asynchronous
    public Future<User> getUser(){
        long id = new Date().getTime();
        User user = new User("User " + id, "Nachname " + id, 22);
        return new AsyncResult<User>(user);
    }
    
    @Asynchronous
    public void doSomeSlowStuff(User user){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

