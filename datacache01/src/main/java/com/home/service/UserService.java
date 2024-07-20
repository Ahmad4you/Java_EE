/**
 * 
 */
package com.home.service;

import java.util.List;

import com.home.model.User;

import datacache.UserCacheBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserService {

    @EJB
    private UserCacheBean userCache;

    public List<User> getAllUsers() {
        return userCache.get();
    }

    public User findUserById(Long id) {
        return userCache.get().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}