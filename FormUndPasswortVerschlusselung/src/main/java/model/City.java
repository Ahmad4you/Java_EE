package model;

import static jakarta.persistence.GenerationType.IDENTITY;

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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	private String name;

	//bi-directional many-to-one association to Zone
	@OneToMany(mappedBy="city")
	private List<Zone> zones;

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

	public List<Zone> getZones() {
		return this.zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public Zone addZone(Zone zone) {
		getZones().add(zone);
		zone.setCity(this);

		return zone;
	}

	public Zone removeZone(Zone zone) {
		getZones().remove(zone);
		zone.setCity(null);

		return zone;
	}

}