/**
 * 
 */
package com.home.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@RequestScoped
public class MeinBean {
    private String name;
    private String ausgabe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAusgabe() {
        return ausgabe;
    }

    public void aktion() {
        ausgabe = "Hallo, " + name + "!";
    }
}