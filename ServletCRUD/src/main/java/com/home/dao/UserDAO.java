/**
 * 
 */
package com.home.dao;

import java.util.List;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserDAO {

	@PersistenceContext(unitName = "AhmadPU")
	private EntityManager entityManager;

	public void addUser(User user) {
		entityManager.persist(user);
	}

	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	public List<User> getAllUsers() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	public void updateUser(User user) {
		entityManager.merge(user);
	}

	public void deleteUser(Long id) {
		User user = getUserById(id);
		if (user != null) {
			entityManager.remove(user);
		}
	}
}
