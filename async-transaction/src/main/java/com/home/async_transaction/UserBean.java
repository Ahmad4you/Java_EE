package com.home.async_transaction;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserBean {
	
	@PersistenceContext
    private EntityManager entityManager;
    
    public User getUser(){
        try {
            TimeUnit.SECONDS.sleep(5);
            long id = new Date().getTime();
            User user = new User("User " + id, "Nachname" + id, 22);
            entityManager.persist(user);
            
            return user;
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            long id = new Date().getTime();
            return new User("Error " + id, "Error", 1);
        }
    }
}
