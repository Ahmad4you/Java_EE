package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.api.GenericDB;
import wolkenag.domain.Buchung;
import wolkenag.model.dao.map.impl.BuchungMapper;

/**
 * 
 * 
 * @author Alrefai
 *
 */

public class BuchungDB implements GenericDB<Buchung>{
	
	
	private Connection connection;

	private BuchungMapper buchungMapper = new BuchungMapper();

	private static final String INSERT_BUCHUNG = "INSERT INTO public.buchung(id_buchung, titel, beschreibung, beginn, ende, catering, extrawunsch, bestaetigung) VALUES (DEFAULT , ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.buchung";
	private static final String SELECT_BY_ID = "SELECT * FROM public.buchung WHERE id_buchung = ?";
	private static final String UPDATE_BUCHUNG = "UPDATE public.buchung SET titel = ?, beschreibung = ?, "
			+ "beginn = ?, ende = ?, catering = ?, extrawunsch = ?, bestaetigung = ? WHERE id_buchung = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.buchung WHERE id_buchung = ?";
	private static final String GET_ID_BY_TITEL = "SELECT * FROM public.buchung WHERE titel = ?";
	private static final String GET_ID_BY_TITEL2 = "SELECT * FROM public.buchung WHERE titel = ? and beginn = ? and ende = ?";
	private static final String UPDATE_TERMINBUACHUNG2 = "UPDATE public.buchung SET catering = ?, extrawunsch = ?, bestaetigung = ? WHERE id_buchung = ?";
	
	public BuchungDB(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public int saveItem(Buchung item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BUCHUNG);
//		preparedStatement.setInt(1, item.getId_buchung());
		preparedStatement.setString(1, item.getTitel());
		preparedStatement.setString(2, item.getBeschreibung());
		preparedStatement.setTimestamp(3, item.getBeginn());
		preparedStatement.setTimestamp(4, item.getEnde());
		preparedStatement.setBoolean(5, item.isCatering());
		preparedStatement.setString(6, item.getExtrawunsch());
		preparedStatement.setBoolean(7, item.isBestaetigung());

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	@Override
	public List<Buchung> findAllItems() throws SQLException {
		List<Buchung> buchungen = new ArrayList<Buchung>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Buchung buchung = buchungMapper.rowMapper(resultSet);
			buchungen.add(buchung);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return buchungen;
	}

	@Override
	public Buchung findById(int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return buchungMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Buchung();
	}

	@Override
	public int updateItem(Buchung item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BUCHUNG);
		preparedStatement.setString(1, item.getTitel());
		preparedStatement.setString(2, item.getBeschreibung());
		preparedStatement.setTimestamp(3, item.getBeginn());
		preparedStatement.setTimestamp(4, item.getEnde());
		preparedStatement.setBoolean(5, item.isCatering());
		preparedStatement.setString(6, item.getExtrawunsch());
		preparedStatement.setBoolean(7, item.isBestaetigung());
		preparedStatement.setInt(8, item.getId_buchung());
		
		updatedRecord =  preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return updatedRecord;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		int deletedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(1, id);

		deletedRecord =  preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return deletedRecord;
	}
	public Buchung findIdByTitel(final String title) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_TITEL);
		preparedStatement.setString(1, title);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return buchungMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Buchung();
	}
	
	public Buchung findIdByTitel(final String title, final Timestamp begin, final Timestamp ende) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_TITEL2);
		preparedStatement.setString(1, title);
		preparedStatement.setTimestamp(2, begin);
		preparedStatement.setTimestamp(3, ende);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return buchungMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Buchung();
	}
	
	
	public List<Buchung> findAllevevts() throws SQLException {
		List<Buchung> buchungen = new ArrayList<Buchung>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			
			Buchung buchung = buchungMapper.eventMapper(resultSet);
			buchungen.add(buchung);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return buchungen;
	}
	public int updateTerminbuchung2(Buchung item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TERMINBUACHUNG2);
		
		preparedStatement.setBoolean(1, item.isCatering());
		preparedStatement.setString(2, item.getExtrawunsch());
		preparedStatement.setBoolean(3, item.isBestaetigung());
		preparedStatement.setInt(4, item.getId_buchung());
		
		updatedRecord =  preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return updatedRecord;
	}

}
