package com.home.hibernate.oneToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.home.hibernate.db.DBGeneric;
import com.home.hibernateCon.entity.Gamer;
import com.home.hibernateCon.entity.emailAddress;

/**
 * one to many
 * Gamer hat 3 emails
 * 
 * @author Ahmad Alrefai
 */
public class MainGeneric {

	public static void main(String[] args) {
		SessionFactory factory = DBGeneric.createSF();
		Session ses = factory.openSession();
		
		ses.beginTransaction();
		
		
		
		emailAddress a1 = new emailAddress("hallo@web.de");
		emailAddress a2 = new emailAddress("ciao@web.de");
		emailAddress a3 = new emailAddress("ahmado@web.de");

		Gamer gamer = new Gamer("Hannah", 24);
		
		Set<emailAddress> emails = new HashSet<emailAddress>();
		emails.add(a1);
		emails.add(a2);
		emails.add(a3);
		
		gamer.setAddresses(emails);
		
		ses.save(gamer);
		
		/*
		 * das Löschen einen Gamer soll damit 3 emails gelösht werden!
		 * 
		 */
//		Gamer gamer = ses.get(Gamer.class, 2);
//		ses.delete(gamer);
		
		ses.getTransaction().commit();
		ses.close();
		factory.close();
	}
}
