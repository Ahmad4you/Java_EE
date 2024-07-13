/**
 * @author Ahmad Alrefai
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import static db.HibernateDBManager.*;

import org.hibernate.service.spi.ServiceException;
import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

import entity.Benutzer;
import exception.DuplicateEmailException;

//@ApplicationScoped
@Stateless
public class BenutzerService {
	private static final Logger LOGGER = Logger.getLogger(BenutzerService.class.getName());

//	@PersistenceUnit(unitName = "AhmadPU") // in vollständige Java EE / Jakarta EE-Servers.
//	private EntityManagerFactory emf;
//	private EntityManagerFactory emf;  // in Java SE-Umgebung [main] soll verwendet
//	private EntityManager em;
	
	@PersistenceContext(unitName = "AhmadPU") // JTA
	private EntityManager em;
	
	String configfile = "javaStorehibernate.cfg.xml";
	
	/*
	 *  in vollständige Java EE / Jakarta EE-Servers.
	 */
//	@PostConstruct
//	public void init() {
//		LOGGER.info("Initializing BenutzerService .........");
//		 if (emf != null) {
//	            em = emf.createEntityManager();
//	            LOGGER.info("EntityManager successfully created");
//	        } else {
//	            LOGGER.error("Error: EntityManagerFactory is null");
//	        }
//	}
	
	/*
	 * in Java SE-Umgebung [main] soll verwendet
	 * (basierend auf der Ausgabe von Weld SE)
	 * 
	 */
//	@PostConstruct
//    public void init() {
//        LOGGER.info("Initializing BenutzerService .........");
//        try {
//            emf = Persistence.createEntityManagerFactory("AhmadPU");
//            em = emf.createEntityManager();
//            LOGGER.info("EntityManager successfully created");
//        } catch (Exception e) {
//            LOGGER.error("Error creating EntityManagerFactory: " + e.getMessage());
//        }
//    }

//	@Transactional
//	public Benutzer createUser(Benutzer benutzer) {
//		Session currentSession = null;
//		setDbConfigFilename(configfile);
//		try {
//			currentSession = getCurrentSession();
//			buildSessionFactory();
//			beginTransaktion();
//        	LOGGER.info("Starting createUser method");
//        	currentSession.persist(benutzer);
//        	commitTransaktion();
//		} catch (HibernateException ex) {
//			doRollbackTransaktion();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//	        if (currentSession != null) {
//	            currentSession.close();
//	        }
//	    }
//		return benutzer;
//	}

	@Transactional
	public Benutzer createUser(Benutzer benutzer) throws DuplicateEmailException {
		if (isEmailAlreadyInUse(benutzer.getEmail())) {
	        throw new DuplicateEmailException("E-Mail-Adresse wird bereits verwendet: " + benutzer.getEmail());
	    }
		
		if(benutzer.getPassword() != null) {
    		String hashedPassword = BCrypt.hashpw(benutzer.getPassword(), BCrypt.gensalt());
    		benutzer.setPassword(hashedPassword);
    	}

	    try {
	        em.persist(benutzer);
//	        em.flush(); // Dies stellt sicher, dass die ID generiert wird
	        return benutzer;
	    } catch (PersistenceException e) {
	        LOGGER.error("Error persisting user: " + e.getMessage(), e);
	        throw new ServiceException("Could not create user due to persistence error", e);
	    } catch (Exception e) {
	        LOGGER.error("Unexpected error creating user: " + e.getMessage(), e);
	        throw new ServiceException("Unexpected error while creating user", e);
	    }
	}

//	@PreDestroy
//	public void cleanup() {
//		if (em != null) {
//			em.close();
//		}
////		if (emf != null) {
////			emf.close();
////		}
//	}

	public Benutzer findUserById(Long id) {
		return em.find(Benutzer.class, id);
	}

	@Transactional
	public Benutzer updateUser(Benutzer benutzer) {
		return em.merge(benutzer);
	}

	@Transactional
	public void deleteUser(Long id) {
		Benutzer benutzer = findUserById(id);
		if (benutzer != null) {
			em.remove(benutzer);
		}
	}

	public boolean isEmailAlreadyInUse(String email) {
	    try {
	        LOGGER.infof("Checking if email is already in use: {}", email);
	        Long count = em.createQuery("SELECT COUNT(b) FROM Benutzer b WHERE b.email = :email", Long.class)
	                .setParameter("email", email)
	                .getSingleResult();
	        return count > 0;
	    } catch (Exception e) {
	        LOGGER.error("Error checking if email is in use", e);
	        throw new RuntimeException("Error checking email uniqueness", e);
	    }
	}

	public Benutzer findUserByEmail(String email) {
		try {
			return em.createQuery("SELECT b FROM Benutzer b WHERE b.email = :email", Benutzer.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * Passwort zuruecksetzen
	 */

	@Transactional
	public void updatePassword(Long userId, String newPassword) {
		Benutzer benutzer = findUserById(userId);
		if (benutzer != null) {
			benutzer.setPassword(newPassword);
			em.merge(benutzer);
		} else {
			throw new EntityNotFoundException("Benutzer mit ID " + userId + " nicht gefunden");
		}
	}

}

 