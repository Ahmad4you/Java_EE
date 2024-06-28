package model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="category_id")
	private int categoryId;

	@Lob
	private String description;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	private String name;

	@Column(name="picture_path")
	private String picturePath;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brands_id")
	private Brand brand;

	public Product() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicturePath() {
		return this.picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}