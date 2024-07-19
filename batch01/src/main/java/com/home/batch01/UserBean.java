/**
 * 
 */
package com.home.batch01;

import java.util.List;
import java.util.Properties;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


/**
 * 
 * @author Ahmad Alrefai
 */
@SuppressWarnings("deprecation")
@Named
@ViewScoped
public class UserBean {

    @PersistenceContext
    EntityManager entityManager;
    
    private List<User> users;
    
    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void run() {
        try {
            JobOperator job = BatchRuntime.getJobOperator();
            long jobId = job.start("access-user", new Properties());
            System.out.println("Job started: " + jobId);
        } catch (JobStartException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> get() {
        return entityManager
                .createQuery("SELECT u FROM User as u", User.class)
                .getResultList();
    }
    
    public List<User> getUsers() {
        if (users == null) {
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        users = entityManager
                .createQuery("SELECT u FROM User as u", User.class)
                .getResultList();
    }

    public String reload() {
        loadUsers();
        return null; // bleibt auf derselben Seite
    }
}
