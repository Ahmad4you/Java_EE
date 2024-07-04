/**
 * 
 */
package com.home.projekt.ecommerce;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.home.projekt.ecommerce.entity.Order;
import com.home.projekt.ecommerce.entity.OrderItem;

/**
 * 
 * @author Ahmad Alrefai
 */

@ApplicationScoped
public class OrderService {
    @Inject
    private Event<OrderEvent> orderEvent;

    @Inject
    private EntityManager entityManager;

    @Inject
    private InventoryService inventoryService;

    @Transactional
    public Order createOrder(List<OrderItem> items, String customerId) {
        // Generiere eine eindeutige Bestellnummer
        String orderId = UUID.randomUUID().toString();

        // Erstelle ein neues Order-Objekt
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCustomerId(customerId);
        order.setStatus("CREATED");

        // Füge Artikel zur Bestellung hinzu und berechne den Gesamtbetrag
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            // Prüfe Verfügbarkeit im Inventar
            if (!inventoryService.isAvailable(item.getProductId(), item.getQuantity())) {
                throw new IllegalStateException("Produkt nicht verfügbar: " + item.getProductId());
            }
            order.addItem(item);
            total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        order.setTotalAmount(total);

        // Speichere die Bestellung in der Datenbank
        entityManager.persist(order);

        // Aktualisiere das Inventar
        for (OrderItem item : items) {
            inventoryService.decreaseStock(item.getProductId(), item.getQuantity());
        }

        // Erzeuge und feuere das OrderEvent
        OrderEvent event = new OrderEvent(orderId, "CREATED");
        orderEvent.fire(event);

        return order;
    }
}