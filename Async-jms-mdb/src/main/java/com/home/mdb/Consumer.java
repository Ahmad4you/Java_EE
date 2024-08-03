/**
 * 
 */
package com.home.mdb;

import com.home.model.User;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;


/**
 * Message-Driven Bean (MDB): das ist eine spezielle Art von Enterprise Java Bean (EJB), die auf JMS-Nachrichten reagiert.
 * MDB empfängt die Nachricht von der Queue und verarbeitet sie in der onMessage-Methode.
 * 
 * Echtzeitverarbeitung: Nachrichten in Echtzeit verarbeitet werden können, wie z.B. in einem Bestellsystem, 
 * einer Chat-Anwendung oder einer Finanzanwendung.
 * 
 * @author Ahmad Alrefai
 */
@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/JmsQueue"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
	})
	public class Consumer  implements MessageListener{
	
		/*
		 * Diese Methode wird aufgerufen, wenn eine Nachricht in der konfigurierten Queue eingeht. 
		 * Die Nachricht wird in ein User-Objekt umgewandelt und ausgegeben.
		 * 
		 */
	    @Override
	    public void onMessage(Message msg) {
	        try {
	            User user = msg.getBody(User.class);
	            System.out.println("User: " + user);
	        } catch (JMSException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
	    
	}