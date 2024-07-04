/**
 * 
 */
package com.home.projekt.ecommerce.entity;
import jakarta.persistence.*;

/**
 * 
 * @author Ahmad Alrefai
 */

@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	private String productId;

	private int quantity;

	// Konstruktoren
	public Inventory() {
	}

	public Inventory(String productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	// Getter und Setter
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
