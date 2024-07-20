/**
 * 
 */
package com.home.messagedriven;

import com.home.model.User;

import datacache.UserCacheBean;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;


/**
 * 
 * @author Ahmad Alrefai
 */
@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/UserProcessing")
	})
	public class UserProcessorMDB implements MessageListener {

	    @EJB
	    private UserCacheBean userCache;

	    @Override
	    public void onMessage(Message message) {
	        try {
	            if (message instanceof TextMessage) {
	                String userId = ((TextMessage) message).getText();
	                User user = userCache.get().stream()
	                        .filter(u -> u.getId().equals(Long.parseLong(userId)))
	                        .findFirst()
	                        .orElse(null);

	                if (user != null) {
	                    // Verarbeiten den Benutzer asynchron
	                    processUser(user);
	                }
	            }
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }

	    private void processUser(User user) {
	        // Implementierung die asynchrone Benutzerverarbeitung
	    }

		
	}