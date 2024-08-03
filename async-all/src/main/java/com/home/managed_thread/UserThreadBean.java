/**
 * 
 */
package com.home.managed_thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.ws.rs.GET;

import jakarta.ejb.Stateless;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserThreadBean {
	@GET
    public User getUser(){
        try {
            TimeUnit.SECONDS.sleep(5);
            long id = new Date().getTime();
            return new User("User " + id, "Nachname " + id, 22);
        } catch (InterruptedException ex) {
            long id = new Date().getTime();
            return new User("Error " + id, "InterruptedException", 1);
        }
    }

}
