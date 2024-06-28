package org.ahmad.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.Branch;
import model.Brand;
import model.Category;
import model.City;
import model.Product;
import model.Size;
import model.Userslogin1;
import model.Zone;

public class HibernateDBManager {
	
	private static SessionFactory dbSessionFactory;
	private static String dbConfigFilename;
	
	public static void setDbConfigFilename(String dbConfigFilename) {
		HibernateDBManager.dbConfigFilename = dbConfigFilename;
	}
	
	

	public static SessionFactory getDbSessionFactory() {
		return dbSessionFactory;
	}

	public static Session getCurrentSession() {
		return HibernateDBManager.getDbSessionFactory().getCurrentSession(); // common repo!
	}
	
	public static void beginTransaktion() {
		dbSessionFactory.getCurrentSession().beginTransaction(); // common repo!
	}
	
	public static void commitTransaktion() {
		dbSessionFactory.getCurrentSession().getTransaction().commit(); // common repo!
	}
	
	public static void doRollbackTransaktion() {
		 try {
			 if(getCurrentSession().getTransaction().isActive()) { dbSessionFactory.getCurrentSession().getTransaction().rollback();}
             
         } catch (Exception rollbackEx) {
             throw new RuntimeException("Rollback failed: " + rollbackEx.getMessage(), rollbackEx);
         }
         throw new RuntimeException("Transaction failed: " );
     
	}


	/**
	 * 
	 * Hibernate_ohne_JPA
	 * Wenn Sie Hibernate ohne JPA verwenden, müssen Sie die Datenbankverbindung 
	 * manuell an die SessionFactory übergeben.
	 * 
	 * @throws Exception
	 */

	public static void buildSessionFactory() throws Exception {
		if(dbSessionFactory != null) {
			return;
		}
		if(dbConfigFilename == null) {
			throw new Exception("Add config file..");
		}
		
		try {
			Configuration configuration = new Configuration().configure(dbConfigFilename);
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			
			MetadataSources sources = new MetadataSources(serviceRegistry);
			// Annotierte Klassen hinzufügen
			sources.addAnnotatedClass(Category.class);
			sources.addAnnotatedClass(Product.class);
			sources.addAnnotatedClass(Branch.class);
			sources.addAnnotatedClass(Brand.class);
			sources.addAnnotatedClass(City.class);
			sources.addAnnotatedClass(Size.class);
			sources.addAnnotatedClass(Zone.class);
			sources.addAnnotatedClass(Userslogin1.class);
//			sources.addAnnotatedClass(AnotherEntity.class);

			// SessionFactory erstellen
			dbSessionFactory = sources.buildMetadata().buildSessionFactory();
			
		} catch(HibernateException ex) {
			dbSessionFactory = null;
			dbConfigFilename = null;
			
			throw ex;
		}
			
		
	}

}
