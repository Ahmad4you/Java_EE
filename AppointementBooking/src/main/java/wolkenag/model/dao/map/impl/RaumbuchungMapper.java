package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import wolkenag.domain.Raumbuchung;
import wolkenag.model.dao.map.GenericMapper;

public class RaumbuchungMapper implements GenericMapper<Raumbuchung>{

	@Override
	public Raumbuchung rowMapper(ResultSet resultSet) throws SQLException {
		int id_raumbuchung = resultSet.getInt("id_raumbuchung");
		int buchung_id = resultSet.getInt("buchung_id");
		int raum_id = resultSet.getInt("raum_id");
		
		return new Raumbuchung(id_raumbuchung, buchung_id, raum_id);
	}

}
