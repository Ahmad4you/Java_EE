package org.ahmad.view;

import static org.ahmad.db.HibernateDBManager.*;

import org.ahmad.service.CategoryService;

import model.Category;

public class DALmain {
	

	public static void main(String[] args) throws Exception {
		
		/*
		 * transaction-type="RESOURCE_LOCAL"
		 * Der EntityManagerFactory wird mit der Persistenzeinheit myPersistenceUnit injiziert.
		 * Der EntityManager wird manuell erstellt und geschlossen.
		 * Transaktionen werden manuell mit entityManager.getTransaction().begin() und 
		 * entityManager.getTransaction().commit() verwaltet.
		 * Transaktionen können auch mit transaction-type="JTA"
		 * 
		 */
		
//		String configfile = "org\\eclipse\\db\\config\\javaStorehibernate.cfg.xml";
		String configfile = "javaStorehibernate.cfg.xml";
		
		setDbConfigFilename(configfile);
		buildSessionFactory();
		
		Category newCategory = new Category("27.Juni"); // Transient Object
		newCategory.setDescription("some Text");
		
		CategoryService cs = new CategoryService();
//		cs.insertCategory(newCategory);
		cs.insertCategory(newCategory);
		
		
		
	}

}
