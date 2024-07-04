/**
 * 
 */
package com.home.geschaeftsprozess;

/**
 * 
 * @author Ahmad Alrefai
 */
public class OrderStatusChangedEvent {
    private String orderId;
    private OrderStatus newStatus;

    public OrderStatusChangedEvent(String orderId, OrderStatus newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    
    public String getOrderId() { return orderId; }
    public OrderStatus getNewStatus() { return newStatus; }
}