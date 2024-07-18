/**
 * 
 */
package com.home.selfManagedBean;

import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;

/**
 * ConcurrencyManagementType.BEAN: Diese Annotation gibt an, 
 * dass die Bean selbst für das Concurrency Management verantwortlich ist, 
 * anstatt dies dem EJB-Container zu überlassen. Mit ConcurrencyManagementType.BEAN wird festgelegt, 
 * dass die Bean-Instanz selbst die Nebenläufigkeit verwaltet.
 * 
 * @author Ahmad Alrefai
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserSelfManagedBean {

    private int userCount;

    public int getUserCount() {
        return userCount;
    }
    
    /*
     * public synchronized void addUser(): Diese Methode erhöht den userCount um 1. Das Schlüsselwort synchronized stellt sicher, 
     * dass nur ein Thread gleichzeitig diese Methode ausführen kann, 
     * was verhindert, dass mehrere Threads gleichzeitig den Zähler erhöhen und möglicherweise zu Inkonsistenzen führen.
     * 
     */
    
    public synchronized void addUser(){
        userCount++;
    }
}