/**
 * 
 */
package com.home.beans;

import com.home.model.User;

import jakarta.ejb.Stateless;

/**
 * zustandslose EJB
 * @author Ahmad Alrefai
 */
@Stateless
public class UserBean {
    
    public User getUser(){
        long ts = System.currentTimeMillis();
        return new User("Bean" + ts, "user" + ts, 33 );        
    }
}
