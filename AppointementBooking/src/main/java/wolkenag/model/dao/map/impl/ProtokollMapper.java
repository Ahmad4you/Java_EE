package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import wolkenag.domain.Protokoll;
import wolkenag.model.dao.map.GenericMapper;

public class ProtokollMapper implements GenericMapper<Protokoll>{

	@Override
	public Protokoll rowMapper(ResultSet resultSet) throws SQLException {
		int id_protokoll = resultSet.getInt("id_protokoll");
		String typ = resultSet.getString("typ");
		String inhalt = resultSet.getString("inhalt");
		Timestamp zeitstempel = resultSet.getTimestamp("zeitstempel");
		

		return new Protokoll(id_protokoll,  typ, inhalt,  zeitstempel);
	}

}
