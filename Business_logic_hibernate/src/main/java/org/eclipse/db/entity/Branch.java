package org.eclipse.db.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the branches database table.
 * 
 */
@Entity
@Table(name="branches")
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String building;

	@Column(nullable=false, length=100)
	private String name;

	@Column(name="street_name", nullable=false, length=100)
	private String streetName;

	@Column(nullable=false, length=20)
	private String telephone;

	@Column(name="working_end", nullable=false)
	private Time workingEnd;

	@Column(name="working_start", nullable=false)
	private Time workingStart;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zone_id", nullable=false)
	private Zone zone1;

	//bi-directional many-to-one association to Zone
//	@ManyToOne
//	@JoinColumn(name="zone_id", nullable=false)
//	private Zone zone2;

	public Branch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Time getWorkingEnd() {
		return this.workingEnd;
	}

	public void setWorkingEnd(Time workingEnd) {
		this.workingEnd = workingEnd;
	}

	public Time getWorkingStart() {
		return this.workingStart;
	}

	public void setWorkingStart(Time workingStart) {
		this.workingStart = workingStart;
	}

	public Zone getZone1() {
		return this.zone1;
	}

	public void setZone1(Zone zone1) {
		this.zone1 = zone1;
	}

//	public Zone getZone2() {
//		return this.zone2;
//	}
//
//	public void setZone2(Zone zone2) {
//		this.zone2 = zone2;
//	}

}