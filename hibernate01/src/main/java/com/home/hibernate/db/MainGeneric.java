package com.home.hibernate.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

public class MainGeneric {
	

	public static void main(String[] args) {
		SessionFactory factory = DBGeneric.createSF();
	    Session ses = factory.openSession();
	    try {
//	        ses.beginTransaction();
	        
	        // Kaskadierung in User-Entität: das Passport-Objekt vor dem User-Objekt gespeichert werden soll, oder die Kaskadierung konfigurieren.
//	        User user = new User("Ahmad", "Developer", 15, new Passport("111111"));
//	        User user = new User("Ahmad", "Developer", 15);
//	        
//	        user.setZugangsdaten(new Zugangsdaten("ahmad@outlook.de", "=)(&%R$§/==?$$"));
//	        ses.save(user);
	        
//	        String passportNo = "1q11v11x1";
//	        Passport existingPassport = ses.createQuery("FROM Passport WHERE passportNo = :no", Passport.class)
//	                                       .setParameter("no", passportNo)
//	                                       .uniqueResult();
//	        
//	        if (existingPassport == null) {
//	            User user = new User("Ahmad", "Developer", 15, new Passport(passportNo));
//	            user.setZugangsdaten(new Zugangsdaten("ahmad@outlook.de", "=)(&%R$§/==?$$"));
//	            ses.save(user);
//	        } else {
//	            System.out.println("Ein Passport mit dieser Nummer existiert bereits.");
//	        }
//	        
//	        ses.getTransaction().commit();
	    	
	    	CRUD crud = new CRUD();
	    	// Beziehung nur in eine Richtung hier User -> Passport, umgekehrt ist unmöglich
//	    	crud.createUser("11934223v45", "Testuser2", "TestNachname2", 24);
	    	
//	    	crud.deleteUser(Long.valueOf(5));
//	    	crud.deleteUser(5L);
	    		    	
	    	System.out.println(crud.readUserData(3L).toString() );
	    	
	    	/* so wird Fehler geschkagen: Beziehung hier in eine Richtung hier User -> Zugangsdaten
	    	ses.beginTransaction();
	    	Zugangsdaten zugangsdaten = new Zugangsdaten("testemail343@web.de", "testpass343");
	    	zugangsdaten.setUser(new User("test343", "nachname343", 33));
	    	ses.merge(zugangsdaten);
	    	ses.getTransaction().commit();
	    	ses.close(); */
	    	
	    	
	    	
	    } catch (ConstraintViolationException e) {
	        System.out.println("Ein Benutzer mit diesem Vor- und Nachnamen existiert bereits.");
	        // Benutzer auffordern, einen anderen Namen einzugeben
	    } catch (Exception e) {
	        if (ses.getTransaction().isActive()) {
	            ses.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (ses != null) {
	            ses.close();
	        }
	        DBGeneric.shutdown();
	    }
	}
}
