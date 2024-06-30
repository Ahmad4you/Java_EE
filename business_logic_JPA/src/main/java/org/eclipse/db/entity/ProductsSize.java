package org.eclipse.db.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the products_size database table.
 * 
 */
@Entity
@Table(name="products_size")
@NamedQuery(name="ProductsSize.findAll", query="SELECT p FROM ProductsSize p")
public class ProductsSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private float price;

	@Column(name="product_id", nullable=false)
	private int productId;

	@Column(name="products_sizecol", length=45)
	private String productsSizecol;

	@Column(nullable=false)
	private int quantity;

	@Column(name="size_id", nullable=false)
	private int sizeId;

	public ProductsSize() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductsSizecol() {
		return this.productsSizecol;
	}

	public void setProductsSizecol(String productsSizecol) {
		this.productsSizecol = productsSizecol;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSizeId() {
		return this.sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

}