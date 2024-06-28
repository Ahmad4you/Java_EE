package wolkenag.domain;

import java.util.Objects;

public class Teilnehmer {
	private int id_teilnehmer;
	private int buchung_id;
	private int mitarbeiter_id;
	private int organisator_id;
	
	
	
	public Teilnehmer(int id_teilnehmer, int buchung_id, int mitarbeiter_id, int organisator_id) {
		super();
		this.id_teilnehmer = id_teilnehmer;
		this.buchung_id = buchung_id;
		this.mitarbeiter_id = mitarbeiter_id;
		this.organisator_id = organisator_id;
	}
	
	public Teilnehmer(int buchung_id, int mitarbeiter_id, int organisator_id) {
		this.buchung_id = buchung_id;
		this.mitarbeiter_id = mitarbeiter_id;
		this.organisator_id = organisator_id;
	}
	
	public Teilnehmer() {
		
	}

	public final int getId_teilnehmer() {
		return id_teilnehmer;
	}

	public final void setId_teilnehmer(int id_teilnehmer) {
		this.id_teilnehmer = id_teilnehmer;
	}

	public final int getBuchung_id() {
		return buchung_id;
	}

	public final void setBuchung_id(int buchung_id) {
		this.buchung_id = buchung_id;
	}

	public final int getMitarbeiter_id() {
		return mitarbeiter_id;
	}

	public final void setMitarbeiter_id(int mitarbeiter_id) {
		this.mitarbeiter_id = mitarbeiter_id;
	}

	public final int getOrganisator_id() {
		return organisator_id;
	}

	public final void setOrganisator_id(int organisator_id) {
		this.organisator_id = organisator_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buchung_id, id_teilnehmer, mitarbeiter_id, organisator_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teilnehmer other = (Teilnehmer) obj;
		return buchung_id == other.buchung_id && id_teilnehmer == other.id_teilnehmer
				&& mitarbeiter_id == other.mitarbeiter_id && organisator_id == other.organisator_id;
	}

	@Override
	public String toString() {
		return "Teilnehmer [id_teilnehmer=" + id_teilnehmer + ", buchung_id=" + buchung_id + ", mitarbeiter_id="
				+ mitarbeiter_id + ", organisator_id=" + organisator_id + "]";
	}
	
	
	
	
}
