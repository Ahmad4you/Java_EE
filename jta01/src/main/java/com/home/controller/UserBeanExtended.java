/**
 * 
 */
package com.home.controller;



import com.home.model.User;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.persistence.*;



/**
 * 
 * @author Ahmad Alrefai
 */

@Stateful
public class UserBeanExtended {
	/*
	 * @PersistenceContext(unitName = "AhmadPU", type = PersistenceContextType.EXTENDED)
	 * 
	 * Lebenszyklus: Der EntityManager bleibt über mehrere Transaktionen hinweg bestehen.
	 * Verhalten: Entitäten bleiben auch nach Transaktionsende verwaltete (managed) Objekte.
	 * Verwendung: Typischerweise in zustandsbehafteten (stateful) EJBs oder in Session-scoped Beans.
	 * EXTENDED: Langlebig, über mehrere Transaktionen hinweg.
	 * EXTENDED: Für komplexe Geschäftsprozesse, die über mehrere Methoden oder Anfragen hinweg Daten bearbeiten.
	 * EXTENDED: Kann mehr Ressourcen beanspruchen, da der EntityManager länger aktiv bleibt.
	 * 
	 * 
	 */

//    @PersistenceContext(unitName = "AhmadPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    private User user;
    
    public void add(User user){
        em.persist(user);
    }
    
    public void update(User user){
        em.merge(user);
    }
    
    public void remove(User user){
        em.remove(user);
    }
    
    public User findById(Long id){ // loadUser
        return em.find(User.class, id);
    }
    

    public void updateUserName(String newName) {
        user.setFirstName(newName);
        // Kein explizites Merge nötig, da der EntityManager erweitert ist
    }

    @Remove
    public void finish() {
        // EntityManager wird erst hier geschlossen
    }
}
