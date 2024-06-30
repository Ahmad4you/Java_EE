package org.eclipse.service;

import static org.eclipse.db.HibernateDBManager.beginTransaktion;
import static org.eclipse.db.HibernateDBManager.commitTransaktion;
import static org.eclipse.db.HibernateDBManager.doRollbackTransaktion;
import static org.eclipse.db.HibernateDBManager.getCurrentSession;
import static org.eclipse.db.HibernateDBManager.getDbSessionFactory;

import java.util.List;

import org.eclipse.db.entity.Product;
import org.eclipse.repository.GenericRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ProductsService implements GenericRepository<Product>{

	@Override
	public int add(Product entity) throws Exception { 
		Session currentSession = getCurrentSession(); 
		try {
			beginTransaktion(); 

			currentSession.persist(entity); // Transient Object to Save record
			commitTransaktion();
			// detatched Objet
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}
		return 0;
	}

	@Override
	public int update(Product entity) throws Exception {

		Session currentSession = getCurrentSession(); // common repo!
//		Transient Object: object created with key word "new"
		try {
			beginTransaktion(); //
//			Categories foundCategory = (Categories) currentSession.get(Categories.class, 20); // id = 20 
//			foundCategory.setDescription("will be updated");
			
			entity = (Product) currentSession.get(Product.class, entity.getId()); // Persistent

			currentSession.persist(entity); // Persistent Object to update record with setting ID
			commitTransaktion();
			// detatched Object
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}
		return 0;
	
	}

	@SuppressWarnings("deprecation")
	@Override
	public void delete(Product entity) throws Exception {
		Session currentSession = getCurrentSession(); 		
		try {
			beginTransaktion(); 
			Product product = (Product) currentSession.get(Product.class, entity.getId()); // Persistent
			if(product != null) {		
				currentSession.delete(product);
			}			
			
			commitTransaktion();
			// detatched Object
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
		}			
	}

	
	@Override
	public List<Product> findAll() throws Exception {
		Session currentSession = getCurrentSession();
	    Transaction transaction = null;
	    List<Product> productsList = null;
		// with Criteria
		try {
			transaction = currentSession.beginTransaction();
	        // Using JPA Criteria API
	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
	        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	        Root<Product> root = criteriaQuery.from(Product.class);
	        criteriaQuery.select(root);

	        Query<Product> query = currentSession.createQuery(criteriaQuery);
	        productsList = query.getResultList();

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
		return productsList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product findById(int id) throws Exception {
		Session currentSession = getCurrentSession(); 
		Product foundproducts = null;
		
		try {
			beginTransaktion(); 
//			foundCategory = (Categories) currentSession.get(Categories.class, id); //get real-object and return null, wenn not found. 
			foundproducts = (Product) currentSession.load(Product.class, id); //to get proxy-object and return object, wenn not found
					
			getDbSessionFactory().close();
		} catch (HibernateException ex) {
			doRollbackTransaktion();
			throw ex;
		}
		return foundproducts;	
	
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Product findByName(String name) throws Exception {
	    Session currentSession = getCurrentSession();
	    Product product = null;

	    try {
	        beginTransaktion();
	        String hql = "FROM Products WHERE name = :name";
	        Query<Product> query = currentSession.createQuery(hql);
	        query.setParameter("name", name);
	        product = (Product) query.getResultList();
	        getDbSessionFactory().close();
	    } catch (HibernateException ex) {
	        doRollbackTransaktion();
	        throw ex;
	    }
	    return product;
	}

}
