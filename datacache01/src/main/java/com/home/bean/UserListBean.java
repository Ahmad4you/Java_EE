/**
 * 
 */
package com.home.bean;

import java.io.Serializable;
import java.util.List;

import com.home.model.User;

import datacache.UserCacheBean;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;

/**
 * 
 * @author Ahmad Alrefai
 */
@ManagedBean
@ViewScoped
public class UserListBean implements Serializable {

    private static final long serialVersionUID = -6431359256056910426L;

	@EJB
    private UserCacheBean userCache;

    private List<User> users;

    @PostConstruct
    public void init() {
        users = userCache.get();
    }

    public List<User> getUsers() {
        return users;
    }

    public int getUserCount() {
        return users.size();
    }
}
