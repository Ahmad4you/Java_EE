package wolkenag.domain;

import java.util.Objects;

public class Raumbuchung {
	private int id_raumbuchung;
	private int buchung_id;
	private int raum_id;
	
	
	public Raumbuchung(int id_raumbuchung, int buchung_id, int raum_id) {
		this.id_raumbuchung = id_raumbuchung;
		this.buchung_id = buchung_id;
		this.raum_id = raum_id;
	}
	
	public Raumbuchung(int buchung_id, int raum_id) {
		this.buchung_id = buchung_id;
		this.raum_id = raum_id;
	}
	public Raumbuchung() {
	}

	public final int getId_raumbuchung() {
		return id_raumbuchung;
	}

	public final void setId_raumbuchung(int id_raumbuchung) {
		this.id_raumbuchung = id_raumbuchung;
	}

	public final int getBuchung_id() {
		return buchung_id;
	}

	public final void setBuchung_id(int buchung_id) {
		this.buchung_id = buchung_id;
	}

	public final int getRaum_id() {
		return raum_id;
	}

	public final void setRaum_id(int raum_id) {
		this.raum_id = raum_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buchung_id, id_raumbuchung, raum_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raumbuchung other = (Raumbuchung) obj;
		return buchung_id == other.buchung_id && id_raumbuchung == other.id_raumbuchung && raum_id == other.raum_id;
	}

	@Override
	public String toString() {
		return "Raumbuchung [id_raumbuchung=" + id_raumbuchung + ", buchung_id=" + buchung_id + ", raum_id=" + raum_id
				+ "]";
	}

	
	
}
