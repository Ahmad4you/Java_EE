/**
 * 
 */
package com.home.jms;

import java.util.Date;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.*;

/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class QueueSender {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void send() throws JMSException {
        MessageProducer producer;
        TextMessage message;

        try (Connection connection = jmsFactory.createConnection(); 
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            
            producer = session.createProducer(jmsQueue);
            message = session.createTextMessage();

            String msg = "Now it is " + new Date();
            message.setText(msg);
            System.out.println("Message sent to queue: " + msg);
            producer.send(message);

            producer.close();
        }
    }
}
