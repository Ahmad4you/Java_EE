/**
 * 
 */
package com.home.controller;

import java.io.Serializable;
import java.util.Date;
import com.home.entity.User;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * 
 * @author Ahmad Alrefai
 */

@ViewScoped
@Named
public class UserView implements Serializable{
    
    private static final long serialVersionUID = -880026325955410318L;
	private String json;
    
    public void loadUser(){
        long now = System.currentTimeMillis();
        User user = new User(now, 
                "User" + now, 
                "user" + now + "@outlook.de",
                Math.random(),
                new Date());
        
        Jsonb jb = JsonbBuilder.create();
        json = jb.toJson(user);
        try {
            jb.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
