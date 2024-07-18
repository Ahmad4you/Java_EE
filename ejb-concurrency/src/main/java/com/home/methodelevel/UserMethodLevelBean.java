/**
 * 
 */
package com.home.methodelevel;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

/**
 * 
 * @author Ahmad Alrefai
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) //redundant
@AccessTimeout(value = 10000)
public class UserMethodLevelBean {

    private int userCount;
    
    @Lock(LockType.READ)
    public int getUserCount(){
        return userCount;
    }
    
    /*
     * @Lock(LockType.WRITE): Diese Annotation wird auf die addUser()-Methode angewendet. 
     * Sie gibt an, dass nur ein Thread zu einer Zeit diese Methode aufrufen kann. 
     * Alle anderen Threads (sowohl Lese- als auch Schreibzugriffe) müssen warten, bis die Methode abgeschlossen ist.
     * 
     */
    
    @Lock(LockType.WRITE)
    public void addUser(){
        userCount++;
    }
}