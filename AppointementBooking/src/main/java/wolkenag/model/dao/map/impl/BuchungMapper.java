package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import wolkenag.domain.Buchung;
import wolkenag.model.dao.map.GenericMapper;

public class BuchungMapper implements GenericMapper<Buchung>{

	@Override
	public Buchung rowMapper(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id_buchung");
		String titel = resultSet.getString("titel");
		String beschreibung = resultSet.getString("beschreibung");
		Timestamp beginn = resultSet.getTimestamp("beginn");
		Timestamp ende = resultSet.getTimestamp("ende");
		boolean catering = resultSet.getBoolean("catering");
		String extrawunsch = resultSet.getString("extrawunsch");
		boolean bestaetigung = resultSet.getBoolean("bestaetigung");
		
		
		return new Buchung( id,  titel,  beschreibung,  beginn,  ende,  catering, extrawunsch,  bestaetigung);
	}
	
	public Buchung eventMapper(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id_buchung");
		String name = resultSet.getString("titel");
		String description = resultSet.getString("beschreibung");
		Timestamp beginn = resultSet.getTimestamp("beginn");
		Timestamp ende = resultSet.getTimestamp("ende");
		
		
		
		return new Buchung( id,  name,  description,  beginn,  ende);
	}
	
	

}
