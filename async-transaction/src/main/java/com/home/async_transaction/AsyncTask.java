package com.home.async_transaction;

import java.util.concurrent.Callable;

import jakarta.enterprise.inject.spi.CDI;

import com.home.model.User;

import jakarta.transaction.*;

/**
 * 
 * 
 * @author Ahmad Alrefai
 */
public class AsyncTask implements Callable<User> {

    private UserTransaction userTransaction;
    private UserBean userBean;

    @Override
    public User call() throws Exception {
    	/*
    	 * Diese Methode führt CDI-Lookups durch, um Instanzen von UserBean und UserTransaction zu erhalten.
    	 */
        performLookups();
        try {
            userTransaction.begin();
            User user = userBean.getUser();
            userTransaction.commit();
            return user;
        } catch (Exception e) {
            userTransaction.rollback();
            throw e;
        }
    }

    private void performLookups() {
        userBean = CDI.current().select(UserBean.class).get();
        userTransaction = CDI.current().select(UserTransaction.class).get();
    }
    
}
