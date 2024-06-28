package wolkenag.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Protokoll {
	private int id_protokoll;
	private String typ;
	private String inhalt;
	private Timestamp zeitstempel;
	
	public Protokoll() {
		
	}

	public Protokoll(int id_protokoll, String typ, String inhalt, Timestamp zeitstempel) {
		this.id_protokoll = id_protokoll;
		this.typ = typ;
		this.inhalt = inhalt;
		this.zeitstempel = zeitstempel;
	}

	public Protokoll(String typ, String inhalt, Timestamp zeitstempel) {
		this.typ = typ;
		this.inhalt = inhalt;
		this.zeitstempel = zeitstempel;
	}

	public final int getId_protokoll() {
		return id_protokoll;
	}

	public final void setId_protokoll(int id_protokoll) {
		this.id_protokoll = id_protokoll;
	}

	public final String getTyp() {
		return typ;
	}

	public final void setTyp(String typ) {
		this.typ = typ;
	}

	public final String getInhalt() {
		return inhalt;
	}

	public final void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public final Timestamp getZeitstempel() {
		return zeitstempel;
	}

	public final void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_protokoll, inhalt, typ, zeitstempel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Protokoll other = (Protokoll) obj;
		return id_protokoll == other.id_protokoll && Objects.equals(inhalt, other.inhalt)
				&& Objects.equals(typ, other.typ) && Objects.equals(zeitstempel, other.zeitstempel);
	}

	@Override
	public String toString() {
		return "Protokoll [id_protokoll=" + id_protokoll + ", typ=" + typ + ", inhalt=" + inhalt + ", zeitstempel="
				+ zeitstempel + "]";
	}
	
	
	
}
