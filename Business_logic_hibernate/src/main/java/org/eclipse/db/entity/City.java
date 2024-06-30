package org.eclipse.db.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=100)
	private String name;

	//bi-directional many-to-one association to Zone
	@OneToMany(mappedBy="city1")
	private List<Zone> zones1;


	public City() {
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

	public List<Zone> getZones1() {
		return this.zones1;
	}

	public void setZones1(List<Zone> zones1) {
		this.zones1 = zones1;
	}

	public Zone addZones1(Zone zones1) {
		getZones1().add(zones1);
		zones1.setCity1(this);

		return zones1;
	}

	public Zone removeZones1(Zone zones1) {
		getZones1().remove(zones1);
		zones1.setCity1(null);

		return zones1;
	}

	

	

}