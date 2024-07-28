/**
 * 
 */
package com.home.repository;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName", User.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }
}