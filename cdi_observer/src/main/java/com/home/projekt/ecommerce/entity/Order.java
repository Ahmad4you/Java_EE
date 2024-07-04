/**
 * 
 */
package com.home.projekt.ecommerce.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Ahmad Alrefai
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;

    private String customerId;
    private String status;
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    // Konstruktoren
    public Order() {}

    // Getter und Setter
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // Hilfsmethode zum Hinzufügen von Items
    public void addItem(OrderItem item) {
        items.add(item);
    }
}
