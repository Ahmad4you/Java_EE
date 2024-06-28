package wolkenag.domain;

import java.util.Objects;

public class Ausstattung {
	private int id_ausstattung;
	private String beschreibung;
	private boolean gebucht;
	private int raum_id;
	
	public Ausstattung() {
		
	}

	public Ausstattung(int id_ausstattung, String beschreibung, boolean gebucht, int raum_id) {
		this.id_ausstattung = id_ausstattung;
		this.beschreibung = beschreibung;
		this.gebucht = gebucht;
		this.raum_id = raum_id;
	}
	public Ausstattung(String beschreibung, boolean gebucht, int raum_id) {
		this.beschreibung = beschreibung;
		this.gebucht = gebucht;
		this.raum_id = raum_id;
	}

	public final int getId_ausstattung() {
		return id_ausstattung;
	}

	public final void setId_ausstattung(int id_ausstattung) {
		this.id_ausstattung = id_ausstattung;
	}

	public final String getBeschreibung() {
		return beschreibung;
	}

	public final void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public final boolean isGebucht() {
		return gebucht;
	}

	public final void setGebucht(boolean gebucht) {
		this.gebucht = gebucht;
	}

	public final int getRaum_id() {
		return raum_id;
	}

	public final void setRaum_id(int raum_id) {
		this.raum_id = raum_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(beschreibung, gebucht, id_ausstattung, raum_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ausstattung other = (Ausstattung) obj;
		return Objects.equals(beschreibung, other.beschreibung) && gebucht == other.gebucht
				&& id_ausstattung == other.id_ausstattung && raum_id == other.raum_id;
	}

	@Override
	public String toString() {
		return "Ausstattung [id_ausstattung=" + id_ausstattung + ", beschreibung=" + beschreibung + ", gebucht="
				+ gebucht + ", raum_id=" + raum_id + "]";
	}

	
	
}
