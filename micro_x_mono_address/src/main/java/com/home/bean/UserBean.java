package com.home.bean;

import java.util.List;

import com.home.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public void add(User user) {
        em.persist(user);
    }

    public void remove(User user) {
        em.remove(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> get() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> pet = cq.from(User.class);
        cq.select(pet);
        TypedQuery<User> q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
