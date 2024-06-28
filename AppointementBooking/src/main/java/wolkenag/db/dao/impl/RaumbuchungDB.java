package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.domain.Raumbuchung;
import wolkenag.model.dao.map.impl.RaumbuchungMapper;

public class RaumbuchungDB {
	private Connection connection;

	private RaumbuchungMapper raumbuchungMapper = new RaumbuchungMapper();
	
	private static final String INSERT_RAUMBUCHUNG = "INSERT INTO public.raumbuchung(id_raumbuchung, buchung_id, raum_id) VALUES (DEFAULT, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.raumbuchung";
	private static final String SELECT_BY_ID = "SELECT * FROM public.raumbuchung WHERE id_raumbuchung = ?";
	private static final String UPDATE_RAUMBUCHUNG = "UPDATE public.raumbuchung SET buchung_id = ?, raum_id = ? WHERE id_raumbuchung = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.raumbuchung WHERE id_raumbuchung = ?";
	
	public RaumbuchungDB(final Connection connection) {
		this.connection = connection;
	}
	
	public int saveItem(final Raumbuchung item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RAUMBUCHUNG);
		preparedStatement.setInt(1, item.getBuchung_id());
		preparedStatement.setInt(2, item.getRaum_id());
		
		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}
	public List<Raumbuchung> findAllItems() throws SQLException {

		List<Raumbuchung> raumbuchungen = new ArrayList<Raumbuchung>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Raumbuchung raumbuchung = raumbuchungMapper.rowMapper(resultSet);
			raumbuchungen.add(raumbuchung);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return raumbuchungen;
	}

	public Raumbuchung findById(final int id) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return raumbuchungMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Raumbuchung();
	}

	public int updateItem(final Raumbuchung item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RAUMBUCHUNG);
		preparedStatement.setInt(1, item.getBuchung_id());
		preparedStatement.setInt(2, item.getRaum_id());
		preparedStatement.setInt(3, item.getId_raumbuchung());
		
		updatedRecord =  preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return updatedRecord;
	}

	public int deleteById(final int id) throws SQLException {
		int deletedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(1, id);

		deletedRecord =  preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return deletedRecord;
	}

}
