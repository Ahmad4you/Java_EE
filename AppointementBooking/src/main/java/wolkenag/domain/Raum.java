package wolkenag.domain;

import java.util.Objects;

public class Raum {

	private int id_raum;
	private String standort;
	
	public Raum() {

	}

	public Raum(String standort) {
		this.standort = standort;
	}

	public Raum(int id_raum, String standort) {
		super();
		this.id_raum = id_raum;
		this.standort = standort;
	}

	public final int getId_raum() {
		return id_raum;
	}

	public final void setId_raum(int id_raum) {
		this.id_raum = id_raum;
	}

	public final String getStandort() {
		return standort;
	}

	public final void setStandort(String standort) {
		this.standort = standort;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_raum, standort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raum other = (Raum) obj;
		return id_raum == other.id_raum && Objects.equals(standort, other.standort);
	}

	@Override
	public String toString() {
		return "Raum [id_raum=" + id_raum + ", standort=" + standort + "]";
	}
	
	
}
