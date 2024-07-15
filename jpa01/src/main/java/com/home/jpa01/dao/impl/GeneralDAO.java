/**
 * 
 */
package com.home.jpa01.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 
 * @author Ahmad Alrefai
 */
public class GeneralDAO<T> {

	private static EntityManagerFactory entityManagerFactory;

	public GeneralDAO() {
		// TODO Auto-generated constructor stub
	}

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("AhmadPU"); // persistence
		} catch (Throwable ex) {
			System.err.println("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public T create(T t) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
		} finally {
			entityManager.close();
		}

		return t;
	}

	public T read(Class<T> classType, Long id) {
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    try {
	        T t = entityManager.find(classType, id);
	        if (t != null) {
	            entityManager.refresh(t);
	        }
	        return t;
	    } finally {
	        entityManager.close();
	    }
	}	

//	public T read(Class<T> classType, Long id) {
//		EntityManager entityManager = null;
//		try {
//			entityManager = entityManagerFactory.createEntityManager();
//			// Versuchen Sie zuerst, das Objekt zu finden
//			T t = entityManager.find(classType, id);
//
//			if (t == null) {
//				// Wenn nichts gefunden wurde, führen Sie eine native Abfrage aus
//				Query query = entityManager.createNativeQuery(
//						"SELECT * FROM " + classType.getSimpleName().toLowerCase() + " WHERE id = :id", classType);
//				query.setParameter("id", id);
//				List<?> results = query.getResultList();
//
//				if (!results.isEmpty()) {
//					t = (T) results.get(0);
//					System.out.println("Gefunden durch native Abfrage: " + t);
//				} else {
//					System.out.println("Keine Daten gefunden, auch nicht durch native Abfrage.");
//				}
//			} else {
//				entityManager.refresh(t);
//			}
//
//			return t;
//		} finally {
//			entityManager.close();
//		}
//	}

//	public T update(T t) {
//		EntityManager entityManager = null;
//		try {
//			entityManager = entityManagerFactory.createEntityManager();
//			entityManager.getTransaction().begin();
//			entityManager.merge(t);
//			entityManager.getTransaction().commit();
//
//			return t;
//		} finally {
//			entityManager.close();
//		}
//	}
	
	public T update(Class<T> classType, Long id, T updatedEntity) {
	    EntityManager entityManager = null;
	    try {
	        entityManager = entityManagerFactory.createEntityManager();
	        entityManager.getTransaction().begin();
	        
	        // Zuerst das existierende Objekt anhand der ID finden
	        T existingEntity = entityManager.find(classType, id);
	        
	        if (existingEntity == null) {
	            throw new EntityNotFoundException("Entity with id " + id + " not found");
	        }
	        
	        // Aktualisieren Sie die Felder des existierenden Objekts
	        updateFields(existingEntity, updatedEntity);
	        
	        // Merge das aktualisierte Objekt
	        T mergedEntity = entityManager.merge(existingEntity);
	        
	        entityManager.getTransaction().commit();

	        return mergedEntity;
	    } catch (Exception e) {
	        if (entityManager != null && entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        if (entityManager != null) {
	            entityManager.close();
	        }
	    }
	}

	// Diese Methode muss in den spezifischen DAO-Klassen überschrieben werden
	protected void updateFields(T existingEntity, T updatedEntity) {
	    // Implementierung in den Unterklassen
	}

//	public boolean delete(Class<T> classType, Long id) {
//		EntityManager entityManager = null;
//		try {
//			entityManager = entityManagerFactory.createEntityManager();
//			entityManager.getTransaction().begin();
//
//			T t = entityManager.getReference(classType, id);
//			if (t != null) {
//				entityManager.remove(t);
//				entityManager.getTransaction().commit();
//				return true;
//			} else {
//				// Exception
//				return false;
//			}
//
//		} finally {
//			entityManager.close();
//		}
//	}

	public boolean delete(Class<T> classType, Long id) {
	    EntityManager entityManager = null;
	    EntityTransaction transaction = null;
	    try {
	        entityManager = entityManagerFactory.createEntityManager();
	        transaction = entityManager.getTransaction();
	        transaction.begin();

	        T t = entityManager.find(classType, id);
	        if (t != null) {
	            entityManager.remove(t);
	            transaction.commit();
	            return true;
	        } else {
	            transaction.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (entityManager != null) {
	            entityManager.close();
	        }
	    }
	}

	
}
