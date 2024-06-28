package model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String description;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	private String name;

	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
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

}