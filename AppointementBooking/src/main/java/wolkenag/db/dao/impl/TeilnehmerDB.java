package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wolkenag.db.config.DatabaseConnection;
import wolkenag.domain.Teilnehmer;
import wolkenag.model.dao.map.impl.TeilnehmerMapper;

public class TeilnehmerDB {
	
	private Connection connection;

	private TeilnehmerMapper teilnehmerMapper = new TeilnehmerMapper();
	
	private static final String INSERT_TEILNEHMER = "INSERT INTO public.teilnehmer(buchung_id, mitarbeiter_id, organisator_id) VALUES (?, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.teilnehmer";
	private static final String SELECT_BY_ID = "SELECT * FROM public.teilnehmer WHERE id_teilnehmer = ?";
	private static final String UPDATE_TEILNEHMER = "UPDATE public.teilnehmer SET buchung_id=?, mitarbeiter_id=?, organisator_id=? WHERE id_teilnehmer = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.teilnehmer WHERE id_teilnehmer = ?";
	
	public TeilnehmerDB(final Connection connection) {
		this.connection = connection;
	}

	public int saveItem(final Teilnehmer item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEILNEHMER);
		preparedStatement.setInt(1, item.getBuchung_id());
		preparedStatement.setInt(2, item.getMitarbeiter_id());
		preparedStatement.setInt(3, item.getOrganisator_id());
		
		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}
	public List<Teilnehmer> findAllItems() throws SQLException {

		List<Teilnehmer> teilnehmern = new ArrayList<Teilnehmer>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Teilnehmer teilnehmer = teilnehmerMapper.rowMapper(resultSet);
			teilnehmern.add(teilnehmer);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return teilnehmern;
	}

	public Teilnehmer findById(final int id) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return teilnehmerMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Teilnehmer();
	}

	public int updateItem(final Teilnehmer item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEILNEHMER);
		preparedStatement.setInt(1, item.getBuchung_id());
		preparedStatement.setInt(2, item.getMitarbeiter_id());
		preparedStatement.setInt(3, item.getOrganisator_id());
		preparedStatement.setInt(4, item.getId_teilnehmer());
		
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
