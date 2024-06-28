package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.api.GenericDB;
import wolkenag.domain.Protokoll;
import wolkenag.model.dao.map.impl.ProtokollMapper;

public class ProtokollDB implements GenericDB<Protokoll>{
	
	private Connection connection;

	private ProtokollMapper protokollMapper = new ProtokollMapper();

	private static final String INSERT_PROTOKOLL = "INSERT INTO public.protokoll(id_protokoll, typ, inhalt, zeitstempel) VALUES (DEFAULT, ?, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.protokoll;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.protokoll WHERE id_protokoll = ?";
	private static final String UPDATE_PROTOKOLL = "UPDATE public.protokoll SET typ = ?, inhalt = ?, zeitstempel = ? WHERE id_protokoll = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.protokoll WHERE id_protokoll = ?";

	public ProtokollDB(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public int saveItem(Protokoll item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROTOKOLL);
		preparedStatement.setString(1, item.getTyp());
		preparedStatement.setString(2, item.getInhalt());
		preparedStatement.setTimestamp(3, item.getZeitstempel());

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	@Override
	public List<Protokoll> findAllItems() throws SQLException {
		List<Protokoll> protokolls = new ArrayList<Protokoll>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Protokoll protokoll = protokollMapper.rowMapper(resultSet);
			protokolls.add(protokoll);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return protokolls;
	}

	@Override
	public Protokoll findById(int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return protokollMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Protokoll();
	}

	@Override
	public int updateItem(Protokoll item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROTOKOLL);
		preparedStatement.setString(1, item.getTyp());
		preparedStatement.setString(2, item.getInhalt());
		preparedStatement.setTimestamp(3, item.getZeitstempel());
		preparedStatement.setInt(4, item.getId_protokoll());
		
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
	
	
}
