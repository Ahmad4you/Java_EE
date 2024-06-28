package wolkenag.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import wolkenag.domain.Teilnehmer;
import wolkenag.model.dao.map.GenericMapper;

public class TeilnehmerMapper implements GenericMapper<Teilnehmer>{

	@Override
	public Teilnehmer rowMapper(ResultSet resultSet) throws SQLException {
		int id_teilnehmer = resultSet.getInt("id_teilnehmer");
		int buchung_id = resultSet.getInt("buchung_id");
		int mitarbeiter_id = resultSet.getInt("mitarbeiter_id");
		int organisator_id = resultSet.getInt("organisator_id");
		
		return new Teilnehmer(id_teilnehmer, mitarbeiter_id, buchung_id, organisator_id);
	}
	

}
