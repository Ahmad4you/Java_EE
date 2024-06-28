package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.api.GenericDB;
import wolkenag.domain.Raum;
import wolkenag.model.dao.map.impl.RaumMapper;

public class RaumDB implements GenericDB<Raum>{
	
	private Connection connection;

	private RaumMapper raumMapper = new RaumMapper();

	private static final String INSERT_RAUM = "INSERT INTO public.raum(id_raum, standort) VALUES (DEFAULT, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.raum;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.raum WHERE id_raum = ?";
	private static final String UPDATE_RAUM = "UPDATE public.raum SET standort = ? WHERE id_raum = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.raum WHERE id_raum = ?";

	public RaumDB(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public int saveItem(Raum item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RAUM);
		preparedStatement.setString(1, item.getStandort());
		
		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	@Override
	public List<Raum> findAllItems() throws SQLException {
		List<Raum> raueme = new ArrayList<Raum>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Raum raum = raumMapper.rowMapper(resultSet);
			raueme.add(raum);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return raueme;
	}

	@Override
	public Raum findById(int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return raumMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Raum();
	}

	@Override
	public int updateItem(Raum item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RAUM);
		preparedStatement.setString(1, item.getStandort());
		preparedStatement.setInt(2, item.getId_raum());
		
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
