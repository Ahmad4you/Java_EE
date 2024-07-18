/**
 * 
 */
package com.home.service.impl;

import java.util.List;

import com.home.model.User;
import com.home.repository.UserService;
import com.home.service.GeneralService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * 
 * @author Ahmad Alrefai
 */


//@Stateless // ejb-container
@ApplicationScoped  // JSF-container
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName = "AhmadPU")
	private EntityManager em;
	
	@Inject
	private GeneralService<User> generalService;
	
	@PostConstruct  // wird beim Server starten aufgerufen wie @Startup
    public void init() {
        generalService.setEntityClass(User.class);
    }


    @Override 
	public void create(User user) {
    	 if (user.getFirstName() == null || user.getLastName() == null 
    	            || user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()) {
    	            throw new IllegalArgumentException("Vor- und Nachname dürfen nicht leer sein.");
    	        }
    	 
    	generalService.create(user);
	}

	@Override
	public User findById(Long id) { 
		// TODO Auto-generated method stub
		return generalService.findById(id);
	}

	@Override
	public User update(Long id, User updatedEntity) {
		if (updatedEntity.getFirstName() == null || updatedEntity.getFirstName().trim().isEmpty() ||
		        updatedEntity.getLastName() == null || updatedEntity.getLastName().trim().isEmpty()) {
		        throw new IllegalArgumentException("Vor- und Nachname dürfen nicht leer sein.");
		    }

		    User existingEntity = generalService.findById(id);
		    if (existingEntity == null) {
		        throw new EntityNotFoundException("User mit ID " + id + " nicht gefunden");
		    }
		    updateFields(existingEntity, updatedEntity);
		    return generalService.update(id, existingEntity);
	}

	private void updateFields(User existingEntity, User updatedEntity) {
		if (updatedEntity.getFirstName() != null && !updatedEntity.getFirstName().trim().isEmpty()) {
	        existingEntity.setFirstName(updatedEntity.getFirstName().trim());
	    }
	    if (updatedEntity.getLastName() != null && !updatedEntity.getLastName().trim().isEmpty()) {
	        existingEntity.setLastName(updatedEntity.getLastName().trim());
	    }
		 if (updatedEntity.getPassport() != null) {
			 existingEntity.setPassport(updatedEntity.getPassport());
		 }
		 if (updatedEntity.getZugangsdaten() != null) {
			 existingEntity.setZugangsdaten(updatedEntity.getZugangsdaten());
		 }
		    
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return generalService.delete(id);
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return generalService.findAll();
	}

	@Override
	public User findByField(String fieldName, Object fieldValue) {
		// TODO Auto-generated method stub
		return generalService.findByField(fieldName, fieldValue);
	}
	
	@Override
    public boolean userExists(String firstName, String lastName) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(u) FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName", Long.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getSingleResult() > 0;
    }
	

//    @Override
//    public List<User> findAll() {
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
//        query.setParameter("username", username);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
    
}

