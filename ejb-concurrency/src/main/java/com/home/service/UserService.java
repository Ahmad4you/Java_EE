/**
 * 
 */
package com.home.service;

import com.home.classlevel.UserClassLevelBean;
import com.home.model.User;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class UserService {

	@EJB
	private UserClassLevelBean userBean;

	@PersistenceContext
	private EntityManager entityManager;

//    @Inject
//    private Pbkdf2PasswordHash passwordHash;

	public void registerNewUser(User user) throws Exception {
		// Logik zur Benutzerregistrierung
		// ...

		// Validierung der Benutzerdaten
		if (!isValidUser(user)) {
			throw new Exception("Ungültige Benutzerdaten");
		}

		// Überprüfen, ob der Benutzername bereits existiert
//		if (entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
//				.setParameter("username", user.getFirstName()).getSingleResult() > 0) {
//			throw new Exception("Benutzername bereits vergeben");
//		}

		// Passwort hashen
//            String hashedPassword = passwordHash.generate(user.getPassword().toCharArray());
//            user.setPassword(hashedPassword);

		// Setzen zusätzlicher Benutzerdetails
//		user.setRegistrationDate(LocalDateTime.now());
//		user.setActive(true);
//		user.setRole("USER");

		// Benutzer in der Datenbank speichern
//		entityManager.persist(user);

		// Bestätigungs-E-Mail senden (hier nur als Platzhalter)
		sendConfirmationEmail(user);

	

	// Erhöhen der Benutzerzahl
	userBean.addUser();
	
	}

	public int getTotalUserCount() {
		return userBean.getUserCount();
	}

	private boolean isValidUser(User user) {
		return user.getFirstName() != null && !user.getFirstName().isEmpty() && user.getZugangsdaten().getCurrentPassword() != null
				&& user.getZugangsdaten().getCurrentPassword().length() >= 8 && user.getZugangsdaten().getEmail() != null 
				&& user.getZugangsdaten().getEmail().contains("@");
	}

	private void sendConfirmationEmail(User user) {
		// Implementieren Sie hier die Logik zum Senden einer Bestätigungs-E-Mail
		System.out.println("Bestätigungs-E-Mail gesendet an: " + user.getZugangsdaten().getEmail());
	}

}