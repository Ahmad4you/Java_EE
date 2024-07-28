/**
 * 
 */
package com.home.bean;

import java.util.List;

import com.home.model.UserAddress;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class UserAddressBean {

    @PersistenceContext
    private EntityManager em;
    
    public void add(UserAddress address){
        em.persist(address);
    }
    
    public void remove(UserAddress address){
        em.remove(address);
    }
    
    public void update(UserAddress address){
        em.merge(address);
    }
    
    public UserAddress findById(Long id){
        return em.find(UserAddress.class, id);
    }
    
    public List<UserAddress> get() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserAddress> cq = cb.createQuery(UserAddress.class);
        Root<UserAddress> pet = cq.from(UserAddress.class);
        cq.select(pet);
        TypedQuery<UserAddress> q = em.createQuery(cq);
        return q.getResultList();
    }    
}
