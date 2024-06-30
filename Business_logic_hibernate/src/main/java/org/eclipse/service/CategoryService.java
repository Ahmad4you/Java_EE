package org.eclipse.service;

import static org.eclipse.db.HibernateDBManager.*;

import java.util.List;
import org.eclipse.db.entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

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
	String configfile = "javaStorehibernate.cfg.xml";
	
	public CategoryService() {

	}
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
		Session currentSession = null;
		setDbConfigFilename(configfile);
		try {
			currentSession = getCurrentSession();
			buildSessionFactory();
			beginTransaktion();

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
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        if (currentSession != null) {
	            currentSession.close();
	        }
	    }
	}

	public void editCategory(Category category) {
		if(category.getName() == null || category.getName().isEmpty()) { // to validate
			 throw new CategoryNotFoundException("Category koennte nicht null sein"); 
		}
		Category foundCategory = new Category();
		Session currentSession = null;
//		Transient Object: it is object created with key word "new"
		setDbConfigFilename(configfile);
		try {
			currentSession = getCurrentSession();
			buildSessionFactory();
			beginTransaktion();
			
			foundCategory = (Category) currentSession.get(Category.class, category.getId()); // Persistent
			if(foundCategory == null) {
//				throw new CategoryNotFoundException("Category with given ID not found");
				throw new EntityNotFoundException("Category not found");
			}
			foundCategory.setName(category.getName());
			foundCategory.setDescription(category.getDescription());

//			currentSession.persist(foundCategory); // Persistent Object to update record with setting ID
//			currentSession.update(foundCategory); // Persistent Object to update record with setting ID
//			currentSession.save(foundCategory); //  Persistent Object to update record with setting ID
			currentSession.merge(foundCategory); //  Transient Object to update record with setting ID
//			currentSession.merge(foundCategory); //  Persistent Object to update record with setting ID
//			currentSession.saveOrUpdate(foundCategory); //  Transient Object to insert record without setting ID
//			currentSession.saveOrUpdate(foundCategory); //  Transient Object to update record with setting ID
//			currentSession.saveOrUpdate(foundCategory); //  Persitent Object to update record with setting ID
			
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			commitTransaktion();
			// detatched Object
	        if (currentSession != null) {
	            currentSession.close();
	        }
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void deleteCategory(int id) {
		Category category = new Category();
		Session currentSession = null;
		setDbConfigFilename(configfile);
		try {
			currentSession = getCurrentSession();
			buildSessionFactory();
			beginTransaktion(); 
			
		    category = (Category) currentSession.get(Category.class, id); // Persistent
			if(category != null) {		
				currentSession.delete(category);
			} else {
				throw new EntityNotFoundException("Category not found");
			}
			
			// detatched Object
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			commitTransaktion();
			// detatched Object
	        if (currentSession != null) {
	            currentSession.close();
	        }
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
		Session currentSession = null;
	    List<Category> categoriesList = null;
		// with Criteria
		// Restrictions
		try {
			setDbConfigFilename(configfile);
			buildSessionFactory();
			currentSession = getCurrentSession();
			beginTransaktion();
	        // Using JPA Criteria API
	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
	        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
	        Root<Category> root = criteriaQuery.from(Category.class);
	        criteriaQuery.select(root);

	        Query<Category> query = currentSession.createQuery(criteriaQuery);
	        categoriesList = query.getResultList();

	       
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			 transaction.commit();	
//			getDbSessionFactory().close();   // do not use..
			if (currentSession != null) {
	            currentSession.close();
	        }
		}
	       
		return categoriesList;
		
	}
	
	public Category findCategoryByIDCriteria(int id) {
		setDbConfigFilename(configfile);
	    Session currentSession = getCurrentSession();
	    Transaction transaction = null;
	    Category foundCategory = null;

	    try {
	    	buildSessionFactory();
	        transaction = currentSession.beginTransaction();

	        // Using JPA Criteria API
	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
	        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
	        Root<Category> root = criteriaQuery.from(Category.class);
	        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

	        foundCategory = currentSession.createQuery(criteriaQuery).uniqueResult();

	        transaction.commit();
	    } catch (HibernateException ex) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw ex;
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
	        if (currentSession != null) {
	            currentSession.close();
	        }
	    }
	    return foundCategory;
	}

//	@SuppressWarnings({ "unchecked", "deprecation" })
//	public Categories findByName(String name) throws Exception {
//	    Session currentSession;
//	    Categories category = null;
//
//	    try {
//	    	currentSession = getCurrentSession();
//	        beginTransaktion();
//	        String hql = "FROM Categories WHERE name = :name";
//	        Query<Categories> query = currentSession.createQuery(hql);
//	        query.setParameter("name", name);
//	        category = (Categories) query.getResultList();
//	        getDbSessionFactory().close();
//	    } catch (HibernateException ex) {
//	        doRollbackTransaktion();
//	        throw ex;
//	    }
//	    return category;
//	}
	
	@SuppressWarnings("serial")
	public class CategoryNotFoundException extends RuntimeException {
	    public CategoryNotFoundException(String message) {
	        super(message);
	    }
	}
	
	
}