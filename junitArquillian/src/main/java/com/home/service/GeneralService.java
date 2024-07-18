/**
 * 
 */
package com.home.service;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;



/**
 * 
 * @author Ahmad Alrefai
 */

@Dependent
public class GeneralService<T> { // GenericRepository
	private static final Logger LOGGER = Logger.getLogger(GeneralService.class.getName());
	private Class<T> entityClass;

	@PersistenceContext(unitName = "AhmadPU")
	private EntityManager em;
	
//	private EntityManagerFactory emf;  // in Java SE-Umgebung [main] soll verwendet
//	private EntityManager em;
	
	@Resource
    private UserTransaction userTransaction; //  manuelle Transaktionsverwaltung

	
	 public GeneralService() {
	        // Default constructor
	    }
	
	public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	@Transactional(Transactional.TxType.REQUIRED)
	public void create(T t) {
		 try {
			 if (em.contains(t)) {
		            em.merge(t);
		        } else {
		            em.persist(t);
		        }
		        em.flush();
	            LOGGER.info("Created entity of type: " + entityClass.getSimpleName());
	        } catch (Exception e) {
	            LOGGER.error("Error creating entity: " + e.getMessage());
	            throw new RuntimeException("Error creating entity", e);
	        }
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public T findById(Long id) {
		 try {
//			 userTransaction.begin();
			 T t = em.find(entityClass, id);
			 if (t != null) {
				 em.refresh(t);
//				 userTransaction.commit();
			 return t;
			 }
		} catch (Exception e) {
			 LOGGER.error("Error finding entity: " + e.getMessage());
		}
		return null;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public T update(Long id, T updatedEntity) {
		T mergedEntity = null;
		try {
			LOGGER.info("Updated entity: " + updatedEntity);
		// Zuerst das existierende Objekt anhand der ID finden
		T existingEntity = em.find(entityClass, id);

		if (existingEntity == null) {
            LOGGER.error("Entity with id " + id + " not found");
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }

		// Aktualisieren Sie die Felder des existierenden Objekts
//		updateFields(existingEntity, updatedEntity); nicht hier in Unterklassen
		
		// Merge das aktualisierte Objekt
		mergedEntity = em.merge(updatedEntity);
		em.flush();
		LOGGER.info("Entity after update: " + mergedEntity);
		} catch (Exception e) {
			LOGGER.error("Error updating entity: " + e.getMessage());
		}
		return mergedEntity;	
	}

	// Diese Methode muss in den spezifischen DAO-Klassen überschrieben werden
	protected void updateFields(T existingEntity, T updatedEntity) {
		// Implementierung in den Unterklassen
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public boolean delete(Long id) {
		try {
			T t = em.find(entityClass, id);
			if (t != null) {
				em.remove(em.merge(t));
				LOGGER.info("record deleted");
				return true;
			} 
		} catch (Exception e) {
			LOGGER.error("Error deleting entity: " + e.getMessage());
		}
		return false;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public List<T> findAll() {
		TypedQuery<T> query = null;
		try {
		String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
		query = em.createQuery(queryString, entityClass);
		} catch (Exception e) {
			LOGGER.error("Error in findall() " + e.getMessage());
		}
		return query.getResultList();
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public T findByField(String fieldName, Object fieldValue) {
		try {
		String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName
				+ " = :fieldValue";
		TypedQuery<T> query = em.createQuery(queryString, entityClass);
		query.setParameter("fieldValue", fieldValue);
			return query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("NoResultException in NoResultException() " + e.getMessage());
			return null;
		}
	}
	
	
	
	/*
	 * in Java SE-Umgebung [main] kann verwendet (basierend auf der Ausgabe von Weld
	 * SE)
	 * 
	 */
//	@PostConstruct
//	public void init() {
//		LOGGER.info("Initializing BenutzerService .........");
//		try {
//			emf = Persistence.createEntityManagerFactory("AhmadPU");
//			em = emf.createEntityManager();
//			LOGGER.info("EntityManager successfully created");
//		} catch (Exception e) {
//			LOGGER.error("Error creating EntityManagerFactory: " + e.getMessage());
//		}
//	}

//	public void create(T t) { RESOURCE_LOCAL
//		emf = Persistence.createEntityManagerFactory("AhmadPU");
//		em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
//		
//		em.persist(t);
//		em.flush();
//		
//		transaction.commit();
//		em.close();
//	}

}