package wolkenag.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Buchung {
	private int id_buchung;
	private String titel;
	private String beschreibung;
	private Timestamp beginn;
	private Timestamp ende;
	private boolean catering;
	private String extrawunsch;
	private boolean bestaetigung;
	
	public Buchung() {
	
	}

	public Buchung(String titel, String beschreibung, Timestamp beginn, Timestamp ende, boolean catering,
			String extrawunsch, boolean bestaetigung) {
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.beginn = beginn;
		this.ende = ende;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
	}

	public Buchung(int id_buchung, String titel, String beschreibung, Timestamp beginn, Timestamp ende, boolean catering,
			String extrawunsch, boolean bestaetigung) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.beginn = beginn;
		this.ende = ende;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
	}
	public Buchung(int id_buchung,  String titel, Timestamp beginn, Timestamp ende) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beginn = beginn;
		this.ende = ende;
	}
	public Buchung(int id_buchung, String titel, String beschreibung ,boolean catering, String extrawunsch, boolean bestaetigung) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
	}
	public Buchung(String titel, String beschreibung , Timestamp beginn, Timestamp ende) {
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.beginn = beginn;
		this.ende = ende;
	}
	public Buchung(int id_buchung, String titel, String beschreibung, Timestamp beginn, Timestamp ende) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.beginn = beginn;
		this.ende = ende;
	}
	public Buchung(int id_buchung, String titel,boolean catering, String extrawunsch, boolean bestaetigung) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
	}
	public Buchung(int id_buchung, String titel, Timestamp beginn, Timestamp ende, boolean catering,
			String extrawunsch, boolean bestaetigung) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beginn = beginn;
		this.ende = ende;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
	}
	
	public Buchung(int id_buchung, String titel, Timestamp beginn, Timestamp ende, boolean catering,
			String extrawunsch) {
		this.id_buchung = id_buchung;
		this.titel = titel;
		this.beginn = beginn;
		this.ende = ende;
		this.catering = catering;
		this.extrawunsch = extrawunsch;
	}

	public Buchung( boolean catering, String extrawunsch, boolean bestaetigung, int id_buchung) {
		
		this.catering = catering;
		this.extrawunsch = extrawunsch;
		this.bestaetigung = bestaetigung;
		this.id_buchung = id_buchung;
	}

	public final int getId_buchung() {
		return id_buchung;
	}

	public final void setId_buchung(int id_buchung) {
		this.id_buchung = id_buchung;
	}

	public final String getTitel() {
		return titel;
	}

	public final void setTitel(String titel) {
		this.titel = titel;
	}

	public final String getBeschreibung() {
		return beschreibung;
	}

	public final void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public final Timestamp getBeginn() {
		return beginn;
	}

	public final void setBeginn(Timestamp beginn) {
		this.beginn = beginn;
	}

	public final Timestamp getEnde() {
		return ende;
	}

	public final void setEnde(Timestamp ende) {
		this.ende = ende;
	}

	public final boolean isCatering() {
		return catering;
	}

	public final void setCatering(boolean catering) {
		this.catering = catering;
	}

	public final String getExtrawunsch() {
		return extrawunsch;
	}

	public final void setExtrawunsch(String extrawunsch) {
		this.extrawunsch = extrawunsch;
	}

	public final boolean isBestaetigung() {
		return bestaetigung;
	}

	public final void setBestaetigung(boolean bestaetigung) {
		this.bestaetigung = bestaetigung;
	}

	@Override
	public int hashCode() {
		return Objects.hash(beginn, beschreibung, bestaetigung, catering, ende, extrawunsch, id_buchung, titel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buchung other = (Buchung) obj;
		return Objects.equals(beginn, other.beginn) && Objects.equals(beschreibung, other.beschreibung)
				&& bestaetigung == other.bestaetigung && catering == other.catering && Objects.equals(ende, other.ende)
				&& Objects.equals(extrawunsch, other.extrawunsch) && id_buchung == other.id_buchung
				&& Objects.equals(titel, other.titel);
	}

	@Override
	public String toString() {
		return "Buchung [id_buchung=" + id_buchung + ", titel=" + titel + ", beschreibung=" + beschreibung + ", beginn="
				+ beginn + ", ende=" + ende + ", catering=" + catering + ", extrawunsch=" + extrawunsch
				+ ", bestaetigung=" + bestaetigung + "]";
	}

	
}
