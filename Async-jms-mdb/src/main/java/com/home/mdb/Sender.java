/**
 * 
 */
package com.home.mdb;

import com.home.model.User;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;


/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class Sender {
    
    @Inject
    private JMSContext context; // zur Erzeugung und zum Senden von JMS-Nachrichten
    
    @Resource(lookup = "jms/JmsQueue")
    private Destination queue;  //  JMS-Queue, an die Nachrichten gesendet werden sollen.
    
    public void send(User user){
        context.createProducer()
                .setDeliveryMode(DeliveryMode.PERSISTENT)
                .setDisableMessageID(true)
                .setDisableMessageTimestamp(true)
                .send(queue, user);
    }
    
}
