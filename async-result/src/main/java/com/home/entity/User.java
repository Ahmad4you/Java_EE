package com.home.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */

public class User implements Serializable{

    private static final long serialVersionUID = 7597489581361416141L;
	private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + '}';
    }
}
