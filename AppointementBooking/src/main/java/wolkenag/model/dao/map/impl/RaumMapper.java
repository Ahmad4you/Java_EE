package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import wolkenag.domain.Raum;
import wolkenag.model.dao.map.GenericMapper;

public class RaumMapper implements GenericMapper<Raum>{

	@Override
	public Raum rowMapper(ResultSet resultSet) throws SQLException {
		int id_raum = resultSet.getInt("id_raum");
		String standort = resultSet.getString("standort");
		
		
		return new Raum(id_raum, standort);
	}

}
