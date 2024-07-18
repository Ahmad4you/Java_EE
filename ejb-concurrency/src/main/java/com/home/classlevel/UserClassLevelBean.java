/**
 * 
 */
package com.home.classlevel;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

/**
 * @Lock(LockType.READ): Diese Annotation legt fest, dass alle Methoden in dieser Klasse standardmäßig einen Read-Lock verwenden sollen. 
 * Das bedeutet, dass mehrere Threads gleichzeitig auf die Methoden zugreifen können, solange sie nur lesend zugreifen.
 * 
 * 
 * @author Ahmad Alrefai
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) //redundant
@Lock(LockType.READ)
@AccessTimeout(value = 10000)
public class UserClassLevelBean {

    private int userCount;
    
    /*
     * public int getUserCount(): Diese Methode gibt den aktuellen Wert von userCount zurück. Da die Klasse mit @Lock(LockType.READ) annotiert ist, 
     * können mehrere Threads diese Methode gleichzeitig aufrufen.
     * 
     * 
     */
    public int getUserCount() {
        return userCount;
    }
    
    @Lock(LockType.WRITE)
    public void addUser(){
        userCount++;
    }

}
