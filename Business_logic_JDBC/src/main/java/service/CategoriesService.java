package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

import domain.Categories;

@Stateless
public class CategoriesService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Categories> findAllTyps() {
        return entityManager.createQuery("SELECT c FROM Categories c", Categories.class).getResultList();
    }
}
