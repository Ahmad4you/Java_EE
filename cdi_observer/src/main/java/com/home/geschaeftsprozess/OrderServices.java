/**
 * 
 */
package com.home.geschaeftsprozess;

import jakarta.enterprise.event.Event;

/**
 * 
 * @author Ahmad Alrefai
 */
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class OrderServices {
    @Inject
    private Event<OrderStatusChangedEvent> statusChangedEvent;

    public void processOrder(String orderId) {
        // Bestellung erstellt
        statusChangedEvent.fire(new OrderStatusChangedEvent(orderId, OrderStatus.CREATED));

        // Weitere Verarbeitungsschritte...
    }

    public void verifyPayment(String orderId) {
        // Zahlungsüberprüfung
        statusChangedEvent.fire(new OrderStatusChangedEvent(orderId, OrderStatus.PAYMENT_VERIFIED));
    }

    public void checkInventory(String orderId) {
        // Lagerbestandsprüfung
        statusChangedEvent.fire(new OrderStatusChangedEvent(orderId, OrderStatus.INVENTORY_CHECKED));
    }

    public void prepareShipment(String orderId) {
        // Versandvorbereitung
        statusChangedEvent.fire(new OrderStatusChangedEvent(orderId, OrderStatus.SHIPMENT_PREPARED));
    }

    public void shipOrder(String orderId) {
        // Versand
        statusChangedEvent.fire(new OrderStatusChangedEvent(orderId, OrderStatus.SHIPPED));
    }
}