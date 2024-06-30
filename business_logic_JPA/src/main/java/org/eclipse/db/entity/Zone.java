package org.eclipse.db.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the zones database table.
 * 
 */
@Entity
@Table(name="zones")
@NamedQuery(name="Zone.findAll", query="SELECT z FROM Zone z")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=100)
	private String name;

	//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="zone1")
	private List<Branch> branches1;


	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city1;


	public Zone() {
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

	public List<Branch> getBranches1() {
		return this.branches1;
	}

	public void setBranches1(List<Branch> branches1) {
		this.branches1 = branches1;
	}

	public Branch addBranches1(Branch branches1) {
		getBranches1().add(branches1);
		branches1.setZone1(this);

		return branches1;
	}

	public Branch removeBranches1(Branch branches1) {
		getBranches1().remove(branches1);
		branches1.setZone1(null);

		return branches1;
	}

	


	public City getCity1() {
		return this.city1;
	}

	public void setCity1(City city1) {
		this.city1 = city1;
	}

	

}