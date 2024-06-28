package wolkenag.domain;

import java.util.Objects;

public class Mitarbeiter {
	
	private int id_mitarbeiter;
	private String nachname;
	private String vorname;
	private String email;
	private String passwort;
	
	public Mitarbeiter() {
		
	}

	public Mitarbeiter(String name, String vorname, String email, String passwort) {
		this.nachname = name;
		this.vorname = vorname;
		this.email = email;
		this.passwort = passwort;
	}


	public Mitarbeiter(int id_mitarbeiter, String name, String vorname, String email, String passwort) {
		this.id_mitarbeiter = id_mitarbeiter;
		this.nachname = name;
		this.vorname = vorname;
		this.email = email;
		this.passwort = passwort;
	}
	public Mitarbeiter(String email, String passwort) {
		this.email = email;
		this.passwort = passwort;
	}
	public Mitarbeiter(int id_mitarbeiter, String email, String passwort) {
		this.id_mitarbeiter = id_mitarbeiter;
		this.email = email;
		this.passwort = passwort;
	}

	public final int getId_mitarbeiter() {
		return id_mitarbeiter;
	}

	public final void setId_mitarbeiter(int id_mitarbeiter) {
		this.id_mitarbeiter = id_mitarbeiter;
	}

	public final String getNachname() {
		return nachname;
	}

	public final void setNachname(String name) {
		this.nachname = name;
	}

	public final String getVorname() {
		return vorname;
	}

	public final void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPasswort() {
		return passwort;
	}

	public final void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id_mitarbeiter, nachname, passwort, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mitarbeiter other = (Mitarbeiter) obj;
		return Objects.equals(email, other.email) && id_mitarbeiter == other.id_mitarbeiter
				&& Objects.equals(nachname, other.nachname) && Objects.equals(passwort, other.passwort)
				&& Objects.equals(vorname, other.vorname);
	}

	@Override
	public String toString() {
		return "Mitarbeiter [id_mitarbeiter=" + id_mitarbeiter + ", name=" + nachname + ", vorname=" + vorname + ", email="
				+ email + ", passwort=" + passwort + "]";
	}

	
}
