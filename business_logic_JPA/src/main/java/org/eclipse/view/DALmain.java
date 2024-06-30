package org.eclipse.view;

import static org.eclipse.db.HibernateDBManager.*;
import org.eclipse.db.entity.Category;
import org.eclipse.service.CategoryService;

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
		
		Category newCategory = new Category(); // Transient Object
		newCategory.setName("Electronics");
		newCategory.setDescription("some Text");
		
		CategoryService cs = new CategoryService();
//		cs.insertCategory(newCategory);
		cs.insertCategory(newCategory);
		
		
		
	}

}
