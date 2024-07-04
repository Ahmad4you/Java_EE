/**
 * 
 */
package com.home.testautomatisierung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Ahmad Alrefai
 */


public class OrderProcessTest {

    @Test
    public void testOrderProcess() {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            OrderProcessor orderProcessor = container.select(OrderProcessor.class).get();
            EventRecorder eventRecorder = container.select(EventRecorder.class).get();

            orderProcessor.processOrder("12345");

            List<Object> events = eventRecorder.getEvents();
            assert events.get(0) instanceof OrderPlacedEvent;
            assert events.get(1) instanceof PaymentProcessedEvent;
            assert events.get(2) instanceof OrderShippedEvent;
        }
    }
}

@ApplicationScoped
class OrderProcessor {
    @Inject
    private Event<Object> eventBus;

    public void processOrder(String orderId) {
        placeOrder(orderId);
        processPayment(orderId);
        shipOrder(orderId);
    }

    private void placeOrder(String orderId) {
        eventBus.fire(new OrderPlacedEvent(orderId));
    }

    private void processPayment(String orderId) {
        eventBus.fire(new PaymentProcessedEvent(orderId));
    }

    private void shipOrder(String orderId) {
        eventBus.fire(new OrderShippedEvent(orderId));
    }
}

@ApplicationScoped
class EventRecorder {
    private List<Object> events = new ArrayList<>();

    public void recordOrderPlacedEvent(@Observes OrderPlacedEvent event) {
        events.add(event);
        System.out.println("Order placed: " + event.getOrderId());
    }

    public void recordPaymentProcessedEvent(@Observes PaymentProcessedEvent event) {
        events.add(event);
        System.out.println("Payment processed for order: " + event.getOrderId());
    }

    public void recordOrderShippedEvent(@Observes OrderShippedEvent event) {
        events.add(event);
        System.out.println("Order shipped: " + event.getOrderId());
    }

    public List<Object> getEvents() {
        return events;
    }
}

// Event-Klassen
class OrderPlacedEvent {
    private String orderId;

    public OrderPlacedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

class PaymentProcessedEvent {
    private String orderId;

    public PaymentProcessedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

class OrderShippedEvent {
    private String orderId;

    public OrderShippedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
