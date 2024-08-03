/**
 * 
 */
package com.home.proxy_task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

/**
 * 
 * @author Ahmad Alrefai
 */
public class UserProxytaskBean {
	
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
