/**
 * 
 */
package com.home.hibernate.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.home.hibernateCon.entity.Passport;
import com.home.hibernateCon.entity.User;

/**
 * 
 * @author Ahmad Alrefai
 */
public class CRUD {
	
	SessionFactory sf = DBGeneric.createSF();
	
	// CRUD Operationen 1. Create 2. read 3. update 4. delete
	
	public void createUser(String passporNo, String firstName, String lastName, int age ) {
		
		/* Directional 1 - 1
		* Kaskadierung in User-Entität
		*/
		Passport passport = new Passport(passporNo);
		User user = new User(firstName, lastName, age, passport);
		
		Session ses = sf.openSession();
		
		ses.beginTransaction();
		
		ses.save(user);
		
		//user.setPassport(passport);
		//ses.save(user);
		
		ses.getTransaction().commit();
		//System.out.println("User hinzugef�gt");
		ses.close();
	}
	
	// read user data
	public User readUserData(Long id) {
		Session ses = sf.openSession();
		
		User userDB = ses.get(User.class, id);
		
		System.out.println("Unser User hat den Namen: " + userDB.getFirstName() + " und das Alter: " + userDB.getAge());
		
		ses.close();
		
		return userDB;
	}
	
	// update user data
	public void updateUserData(String firstName, Long id) {
		Session ses = sf.openSession();
		
		User userDB = ses.get(User.class, id);
		userDB.setFirstName(firstName);
		
		ses.beginTransaction();
		
		ses.update(userDB);
		
		ses.getTransaction().commit();
		ses.close();
	}
	
	// delete user 
	public void deleteUser(Long id) {
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		User userDB = ses.get(User.class, id);
		
		ses.delete(userDB);
		
		ses.getTransaction().commit();
		ses.close();
		
	}

	

}
