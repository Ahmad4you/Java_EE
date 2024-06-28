package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import wolkenag.domain.Ausstattung;
import wolkenag.model.dao.map.GenericMapper;

public class AusstattungMapper implements GenericMapper<Ausstattung>{

	@Override
	public Ausstattung rowMapper(ResultSet resultSet) throws SQLException {
		int id_ausstattung = resultSet.getInt("id_ausstattung");
		String beschreibung = resultSet.getString("beschreibung");
		boolean gebucht = resultSet.getBoolean("gebucht");
		int raum_id = resultSet.getInt("raum_id");
		
		return new Ausstattung( id_ausstattung,  beschreibung,  gebucht, raum_id);
	}

}
