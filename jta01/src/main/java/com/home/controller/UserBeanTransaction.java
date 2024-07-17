/**
 * 
 */
package com.home.controller;



import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;


/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserBeanTransaction {
	/*
	 * @PersistenceContext(unitName = "AhmadPU", type = PersistenceContextType.TRANSACTION)
	 * Dies ist der Standardtyp, wenn kein Typ explizit angegeben wird.
	 * 
	 * Lebenszyklus: Der EntityManager wird für die Dauer einer einzelnen Transaktion erstellt und existiert.
	 * Verhalten: Nach dem Abschluss der Transaktion wird der EntityManager geschlossen, und alle verwalteten Entitäten werden abgetrennt (detached).
	 * Verwendung: Typischerweise in zustandslosen (stateless) EJBs oder in Request-scoped Beans.
	 * TRANSACTION: Kurzlebig, nur für eine Transaktion.
	 * 
	 * 
	 */
//    @PersistenceContext(unitName = "AhmadPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    public void add(User user){
        em.persist(user);
    }
    
    public void update(User user){
        em.merge(user);
    }
    
    public void remove(User user){
        em.remove(user);
    }
    
    public User findById(Long id){
        return em.find(User.class, id);
    }

}
