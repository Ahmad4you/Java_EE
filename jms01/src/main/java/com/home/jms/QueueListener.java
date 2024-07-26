/**
 * 
 */
package com.home.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * JMS (Java Message Service)
 * Nachrichten über eine Queue zu senden und zu empfangen.
 * Message-Driven Bean (MDB), das auf Nachrichten in einer JMS-Queue lauscht
 * 
 * @author Ahmad Alrefai
 */
@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/JmsQueue"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
	})
	public class QueueListener implements MessageListener {

	    @Override
	    public void onMessage(Message message) {
	        TextMessage textMessage = (TextMessage) message;
	        try {
	            System.out.print("new message on queue: ");
	            System.out.println(textMessage.getText());
	            System.out.println();
	        } catch (JMSException e) {
	            System.err.println(e.getMessage());
	        }
	    }
	}