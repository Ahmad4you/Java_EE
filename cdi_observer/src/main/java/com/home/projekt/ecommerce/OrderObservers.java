/**
 * 
 */
package com.home.projekt.ecommerce;

import jakarta.enterprise.event.Observes;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;

/**
 * definition mehrere Observer mit unterschiedlichen Prioritäten
 * 
 * @author Ahmad Alrefai
 */
@ApplicationScoped
public class OrderObservers {

    public void logOrder(@Observes(notifyObserver = Reception.ALWAYS) OrderEvent event) {
        System.out.println("Logging order: " + event.getOrderId());
    }

    public void processPayment(@Observes @Priority(100) OrderEvent event) {
        System.out.println("Processing payment for order: " + event.getOrderId());
        // Implementieren Sie hier die Zahlungslogik
    }

    public void updateInventory(@Observes @Priority(200) OrderEvent event) {
        System.out.println("Updating inventory for order: " + event.getOrderId());
        // Implementieren Sie hier die Bestandsaktualisierungslogik
    }

    public void sendNotification(@Observes(during = TransactionPhase.AFTER_SUCCESS) 
                                 @Priority(300) OrderEvent event) {
        System.out.println("Sending notification for order: " + event.getOrderId());
        // Implementieren Sie hier die Benachrichtigungslogik
    }
}
