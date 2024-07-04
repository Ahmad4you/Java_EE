/**
 * 
 */
package com.home.projekt.ecommerce;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
/**
 * 
 *  Überprüfung der Verfügbarkeit von Produkten und zur Aktualisierung des Lagerbestands.
 * @author Ahmad Alrefai
 */

@ApplicationScoped
public class InventoryService {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isAvailable(String productId, int quantity) {
        Long availableQuantity = (Long) entityManager.createQuery(
                "SELECT i.quantity FROM Inventory i WHERE i.productId = :productId")
                .setParameter("productId", productId)
                .getSingleResult();
        return availableQuantity != null && availableQuantity >= quantity;
    }

    @Transactional
    public void decreaseStock(String productId, int quantity) {
        entityManager.createQuery(
                "UPDATE Inventory i SET i.quantity = i.quantity - :quantity " +
                "WHERE i.productId = :productId AND i.quantity >= :quantity")
                .setParameter("quantity", quantity)
                .setParameter("productId", productId)
                .executeUpdate();
    }
}
