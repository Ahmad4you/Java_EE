package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import wolkenag.domain.Mitarbeiter;
import wolkenag.model.dao.map.GenericMapper;

public class MitarbeiterMapper implements GenericMapper<Mitarbeiter>{

	public Mitarbeiter rowMapper(final ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id_mitarbeiter");
		String name = resultSet.getString("nachname");
		String vorname = resultSet.getString("vorname");
		String email = resultSet.getString("email");
		String passwort = resultSet.getString("passwort");

		return new Mitarbeiter( id,  name,  vorname,  email, passwort);
	}

}
