package org.eclipse.db;

import org.eclipse.db.entity.Brand;
import org.eclipse.db.entity.Category;

import org.eclipse.db.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionManager {
    
    private static SessionFactory sessionFactory;
    private static String configFilename;
    
    public static void setConfigFilename(String configFilename) {
        HibernateSessionManager.configFilename = configFilename;
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        return HibernateSessionManager.getSessionFactory().getCurrentSession();
    }
    
    public static void beginTransaction() {
        sessionFactory.getCurrentSession().beginTransaction();
    }
    
    public static void commitTransaction() {
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    public static void rollbackTransaction() {
        try {
            if (getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        } catch (Exception rollbackEx) {
            throw new RuntimeException("Rollback failed: " + rollbackEx.getMessage(), rollbackEx);
        }
        throw new RuntimeException("Transaction failed.");
    }

    /**
     * 
     * Hibernate without JPA
     * If you are using Hibernate without JPA, you need to manually pass the database connection
     * to the SessionFactory.
     * 
     * @throws Exception
     */
    public static void buildSessionFactory() throws Exception {
        if (sessionFactory != null) {
            return;
        }
        if (configFilename == null) {
            throw new Exception("Add config file.");
        }
        
        try {
            Configuration configuration = new Configuration().configure(configFilename);
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            
            MetadataSources sources = new MetadataSources(serviceRegistry);
            // Add annotated classes
            sources.addAnnotatedClass(Category.class);
            sources.addAnnotatedClass(Product.class);
//            sources.addAnnotatedClass(Branches.class);
            sources.addAnnotatedClass(Brand.class);
//            sources.addAnnotatedClass(Cities.class);
//            sources.addAnnotatedClass(Sizes.class);
//            sources.addAnnotatedClass(Zones.class);
            // sources.addAnnotatedClass(AnotherEntity.class);

            // Build SessionFactory
            sessionFactory = sources.buildMetadata().buildSessionFactory();
            
        } catch (HibernateException ex) {
            sessionFactory = null;
            configFilename = null;
            
            throw ex;
        }
    }
}