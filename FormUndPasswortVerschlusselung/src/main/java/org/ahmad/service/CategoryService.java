package org.ahmad.service;

import static org.ahmad.db.HibernateDBManager.beginTransaktion;
import static org.ahmad.db.HibernateDBManager.commitTransaktion;
import static org.ahmad.db.HibernateDBManager.doRollbackTransaktion;
import static org.ahmad.db.HibernateDBManager.getCurrentSession;
import static org.ahmad.db.HibernateDBManager.getDbSessionFactory;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import model.Category;

/*
 * transaction-type="RESOURCE_LOCAL"
 * Der EntityManagerFactory wird mit der Persistenzeinheit myPersistenceUnit injiziert.
 * Der EntityManager wird manuell erstellt und geschlossen.
 * Transaktionen werden manuell mit entityManager.getTransaction().begin() und 
 * entityManager.getTransaction().commit() verwaltet.
 * Transaktionen können auch mit transaction-type="JTA"
 * 
 */


public class CategoryService {
	
	/*
	 * HQL: Hibernate Query Language
	 */
	
//	@PersistenceContext(unitName = "myPersistenceUnit")
//    private EntityManager entityManager;

//    @Resource
//    private UserTransaction userTransaction;

//	@PersistenceUnit(unitName = "myPersistenceUnit")
//	private EntityManagerFactory entityManagerFactory;
//
//	public void saveCategory(Categories category) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(category);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//	}
//
//	public Categories findCategory(Long id) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		Categories category = entityManager.find(Categories.class, id);
//		entityManager.close();
//		return category;
//	}

	public void insertCategory(Category newCategory) { 
		Session currentSession = getCurrentSession(); // common repo!
		try {
			beginTransaktion(); //

//			@SuppressWarnings("deprecation")
//			Integer savedCategoryId = (Integer) currentSession.save(newCategory);  // Persistent Object
//			if (savedCategoryId != 0) {
//				/*
//				 * Exception in thread "main" org.hibernate.HibernateException: Calling method
//				 * 'save' is not valid without an active transaction (Current status:
//				 * NOT_ACTIVE)
//				 */
//				System.out.println(savedCategoryId);
//			}
			currentSession.persist(newCategory); // Transient Object to Save record
			commitTransaktion();
			// detatched Object
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}

	}

	public void updateCategory(Category newCategory, int id) {
		Session currentSession = getCurrentSession(); // common repo!
//		Transient Object: object created with key word "new"
		try {
			beginTransaktion(); //
//			Categories foundCategory = (Categories) currentSession.get(Categories.class, 20); // id = 20 
//			foundCategory.setDescription("will be updated");
			
			newCategory = (Category) currentSession.get(Category.class, id); // Persistent

			currentSession.persist(newCategory); // Persistent Object to update record with setting ID
//			currentSession.update(newCategory); // Persistent Object to update record with setting ID
//			currentSession.save(newCategory); //  Persistent Object to update record with setting ID
//			currentSession.merge(newCategory); //  Transient Object to update record with setting ID
//			currentSession.merge(newCategory); //  Persistent Object to update record with setting ID
//			currentSession.saveOrUpdate(newCategory); //  Transient Object to insert record without setting ID
//			currentSession.saveOrUpdate(newCategory); //  Transient Object to update record with setting ID
//			currentSession.saveOrUpdate(newCategory); //  Persitent Object to update record with setting ID
			commitTransaktion();
			// detatched Object
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void deleteCategory(int id) {
		Session currentSession = getCurrentSession(); 
		
		try {
			beginTransaktion(); 
			Category category = (Category) currentSession.get(Category.class, id); // Persistent
			if(category != null) {		
				currentSession.delete(category);
			}
			
			
			commitTransaktion();
			// detatched Object
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public Category findCategoryByID(int id) {
		Session currentSession = getCurrentSession(); 
		Category foundCategory = null;
		
		try {
			beginTransaktion(); 
//			foundCategory = (Categories) currentSession.get(Categories.class, id); //get real-object and return null, wenn not found. 
			foundCategory = (Category) currentSession.load(Category.class, id); //to get proxy-object and return object, wenn not found
					
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		}
		return foundCategory;	
	}
	
	public List<Category> findAllCategoriesNative(){
		Session currentSession = getCurrentSession(); 
		List<Category> categories = null;
        String nativeQuery = "SELECT * FROM categories";
		try {
			beginTransaktion(); 

			 Query<Category> query = currentSession.createNativeQuery(nativeQuery, Category.class);
	            categories = query.getResultList();
	            
	            commitTransaktion();
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		}
		return categories;
		
	}
	
	@Transactional
	public List<Category> findAllCategoriesHQL(){
		Session currentSession = getCurrentSession(); 
		String hqlQuery = "FROM Categories";
        List<Category> categoriesList = null;
		// with HQL
		try {
			beginTransaktion(); 
			TypedQuery<Category> query = currentSession.createQuery(hqlQuery, Category.class);
            categoriesList = query.getResultList();
			
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		}
		return categoriesList;
		
	}
	
	public List<Category> findAllCategoriesCriteria(){
		Session currentSession = getCurrentSession();
	    Transaction transaction = null;
	    List<Category> categoriesList = null;
		// with Criteria
		// Restrictions
		try {
			transaction = currentSession.beginTransaction();

	        // Using JPA Criteria API
	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
	        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
	        Root<Category> root = criteriaQuery.from(Category.class);
	        criteriaQuery.select(root);

	        Query<Category> query = currentSession.createQuery(criteriaQuery);
	        categoriesList = query.getResultList();

	        transaction.commit();	
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		} finally {
			getDbSessionFactory().close();
		}
	        if (currentSession != null) {
	            currentSession.close();
	        }
		return categoriesList;
		
	}
	
//	public Category findCategoryByIDCriteria(int id) {
//	    Session currentSession = getCurrentSession();
//	    Transaction transaction = null;
//	    Categories foundCategory = null;
//
//	    try {
//	        transaction = currentSession.beginTransaction();
//
//	        // Using JPA Criteria API
//	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
//	        CriteriaQuery<Categories> criteriaQuery = criteriaBuilder.createQuery(Categories.class);
//	        Root<Categories> root = criteriaQuery.from(Categories.class);
//	        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
//
//	        foundCategory = currentSession.createQuery(criteriaQuery).uniqueResult();
//
//	        transaction.commit();
//	    } catch (HibernateException ex) {
//	        if (transaction != null) {
//	            transaction.rollback();
//	        }
//	        throw ex;
//	    } finally {
//	        if (currentSession != null) {
//	            currentSession.close();
//	        }
//	    }
//
//	    return foundCategory;
//	}
//
//	@SuppressWarnings({ "unchecked", "deprecation" })
//	public List<Categories> findByName(String name) throws Exception {
//	    Session currentSession = getCurrentSession();
//	    List<Categories> productsList = null;
//
//	    try {
//	        beginTransaktion();
//	        String hql = "FROM Categories WHERE name = :name";
//	        Query<Categories> query = currentSession.createQuery(hql);
//	        query.setParameter("name", name);
//	        productsList = query.getResultList();
//	        getDbSessionFactory().close();
//	    } catch (HibernateException ex) {
//	        doRollbackTransaktion();
//	        throw ex;
//	    }
//	    return productsList;
//	}
//	
//	
	
}