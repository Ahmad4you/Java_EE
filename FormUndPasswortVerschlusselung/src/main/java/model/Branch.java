package model;

import static jakarta.persistence.GenerationType.IDENTITY;

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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	private String building;

	private String name;

	@Column(name="street_name")
	private String streetName;

	private String telephone;

	@Column(name="working_end")
	private Time workingEnd;

	@Column(name="working_start")
	private Time workingStart;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	private Zone zone;

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

	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

}