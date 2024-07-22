/**
 * 
 */
package com.home.beans;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import com.home.model.User;
import com.home.roles.Roles;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;



/**
 * 
 * @author Ahmad Alrefai
 */
@Stateful
public class UserBean {
    private Logger logger = Logger.getLogger(UserBean.class.getName());
    
    @PersistenceContext(unitName = "AhmadPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Inject
    private Principal principal;

    @Resource
    private SessionContext sessionContext;
    
    @RolesAllowed({Roles.ADMIN, Roles.OPERATOR, Roles.DATENSCHUTZ})
    public void add(User user){
    	logger.info("User " + principal.getName() + " is trying to create USER.");
        logger.info("User roles: " + sessionContext.getCallerPrincipal().getName());
        em.persist(user);
    }
    
    @RolesAllowed({Roles.ADMIN})
    public void remove(User user){
    	logger.info("User " + principal.getName() + " is trying to delete.");
        logger.info("User roles: " + sessionContext.getCallerPrincipal().getName());
        em.remove(user);
    }
    
    /*
     * WFLYEJB0364: Invocation on method: public void com.home.beans.UserBean.update(com.home.model.User) of bean: UserBean is not allowed
     * 
     * Benutzer hat kein Berichtigung
     */
    
//    @RolesAllowed({"*"})  // Erlaubt allen authentifizierten Benutzern den Zugriff
    @RolesAllowed({Roles.ADMIN})
    public void update(User user){
    	logger.info("User " + principal.getName() + " is trying to update.");
        logger.info("User roles: " + sessionContext.getCallerPrincipal().getName());
        
        em.merge(user);
    }
    
    @PermitAll
    public List<User> get(){

        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
}

