package model;

import static jakarta.persistence.GenerationType.IDENTITY;

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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	private String name;

	//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="zone")
	private List<Branch> branches;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

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

	public List<Branch> getBranches() {
		return this.branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch addBranch(Branch branch) {
		getBranches().add(branch);
		branch.setZone(this);

		return branch;
	}

	public Branch removeBranch(Branch branch) {
		getBranches().remove(branch);
		branch.setZone(null);

		return branch;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}