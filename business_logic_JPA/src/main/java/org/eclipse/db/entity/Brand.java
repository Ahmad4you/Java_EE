package org.eclipse.db.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name = "brands")
@NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "logo_path", length = 45)
	private String logoPath;

	@Column(nullable = false, length = 45)
	private String name;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "brand")
	private List<Product> products;

	public Brand() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogoPath() {
		return this.logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setBrand(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setBrand(null);

		return product;
	}

}