/**
 * 
 */
package com.home.geschaeftsprozess;

import jakarta.enterprise.context.ApplicationScoped;
/**
 * 
 * @author Ahmad Alrefai
 */
import jakarta.enterprise.event.Observes;
import jakarta.annotation.Priority;
import jakarta.interceptor.Interceptor;

@ApplicationScoped
public class OrderProcessObservers {

    public void onOrderCreated(@Observes @Priority(Interceptor.Priority.APPLICATION) OrderStatusChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.CREATED) {
            System.out.println("Bestellung " + event.getOrderId() + " wurde erstellt. Starte Zahlungsüberprüfung.");
            // Hier könnte die Zahlungsüberprüfung gestartet werden
        }
    }

    public void onPaymentVerified(@Observes @Priority(Interceptor.Priority.APPLICATION - 10) OrderStatusChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.PAYMENT_VERIFIED) {
            System.out.println("Zahlung für Bestellung " + event.getOrderId() + " wurde überprüft. Prüfe Lagerbestand.");
            // Hier könnte die Lagerbestandsprüfung gestartet werden
        }
    }

    public void onInventoryChecked(@Observes @Priority(Interceptor.Priority.APPLICATION - 20) OrderStatusChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.INVENTORY_CHECKED) {
            System.out.println("Lagerbestand für Bestellung " + event.getOrderId() + " wurde geprüft. Bereite Versand vor.");
            // Hier könnte die Versandvorbereitung gestartet werden
        }
    }

    public void onShipmentPrepared(@Observes @Priority(Interceptor.Priority.APPLICATION - 30) OrderStatusChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.SHIPMENT_PREPARED) {
            System.out.println("Versand für Bestellung " + event.getOrderId() + " wurde vorbereitet. Versende Bestellung.");
            // Hier könnte der Versandprozess gestartet werden
        }
    }

    public void onOrderShipped(@Observes @Priority(Interceptor.Priority.APPLICATION - 40) OrderStatusChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.SHIPPED) {
            System.out.println("Bestellung " + event.getOrderId() + " wurde versandt. Prozess abgeschlossen.");
            // Hier könnten abschließende Aktionen durchgeführt werden
        }
    }
}