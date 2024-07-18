/**
 * 
 */
package com.home.service;

import com.home.methodelevel.UserMethodLevelBean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserMethodelLevelService {
	
	@EJB
	private UserMethodLevelBean userBean;

	public void registerNewUser() {
        // Geschäftslogik hier...
        userBean.addUser();
    }
    
    public int getTotalUsers() {
        return userBean.getUserCount();
    }
}
