package service;

import static org.ahmad.db.HibernateDBManager.*;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import model.Userslogin1;

@Stateless
public class Userslogin1Service {

//	private EntityManagerFactory emf;
//	private EntityManager em;
	private String configfile = "javaStorehibernate.cfg.xml";
 

	public Userslogin1Service() {
//		emf = Persistence.createEntityManagerFactory("your-persistence-unit");
//		em = emf.createEntityManager();	
	}

	public void registerUser(String username, String plainTextPassword) throws Exception {
		Session session = null;
		try {
			setDbConfigFilename(configfile);
			buildSessionFactory();
			session = getCurrentSession();
			beginTransaktion();

			Userslogin1 userloginEntity = new Userslogin1();
			userloginEntity.setUsername(username);
			userloginEntity.setPasswordHash(hashPassword(plainTextPassword));

			session.persist(userloginEntity);
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			throw e;
		} finally {
			session.getTransaction().commit();
//			session.close();
			getDbSessionFactory().close();
		}
	}

	// Diese Methode würde verwendet werden, um ein neues Passwort zu hashen, bevor
	// es in der Datenbank gespeichert wird
	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	@Transactional
	public List<Userslogin1> getAllUsers() throws Exception {
		setDbConfigFilename(configfile);
		buildSessionFactory();
		Session currentSession = null;
		Transaction transaction = null;
		List<Userslogin1> usersList = null;
		// with Criteria
		try {
			currentSession = getCurrentSession();
			transaction = currentSession.beginTransaction();
			// Using JPA Criteria API
			CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
			CriteriaQuery<Userslogin1> criteriaQuery = criteriaBuilder.createQuery(Userslogin1.class);
			Root<Userslogin1> root = criteriaQuery.from(Userslogin1.class);
			criteriaQuery.select(root);

			Query<Userslogin1> query = currentSession.createQuery(criteriaQuery);
			usersList = query.getResultList();

			transaction.commit();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		} finally {
//			getDbSessionFactory().close();
		}
		if (currentSession != null) {
			currentSession.close();
		}
		return usersList;
	}
	
	public Userslogin1 findByName(String username) throws Exception {
		setDbConfigFilename(configfile);
		buildSessionFactory();
		Session currentSession = null;

		try {
			currentSession = getCurrentSession();
			beginTransaktion();
			String hql = "FROM Userslogin1 u WHERE u.username = :username";
	        Query<Userslogin1> query = currentSession.createQuery(hql, Userslogin1.class);
	        query.setParameter("username", username);
	        Userslogin1 result = query.uniqueResult();
	        commitTransaktion();
	        return result;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// Log the exception
			throw new RuntimeException("Error finding user by name", e);
		}

	}
}
