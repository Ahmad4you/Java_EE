package com.home.hibernate.db;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBGeneric {
	private static final Logger LOGGER = Logger.getLogger(DBGeneric.class.getName());
	private static SessionFactory sf;

	public static SessionFactory createSF() {
		if (sf == null) {
			try {
				// Create SessionFactory from hibernate.cfg.xml

				StandardServiceRegistry registery = new StandardServiceRegistryBuilder().configure().build();

				sf = new MetadataSources(registery).buildMetadata().buildSessionFactory();

				LOGGER.info("-> SF erstellet");
			} catch (Exception e) {
				LOGGER.throwing("DBGeneric", "-> SF konnte nicht erstellt werden.", e);
			}
		}
		return sf;
	}
	
	
	public static void shutdown() {
        if (sf != null) {
            sf.close();
        }
    }
	
	
}
