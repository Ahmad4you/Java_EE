package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.api.GenericDB;
import wolkenag.domain.Ausstattung;
import wolkenag.model.dao.map.impl.AusstattungMapper;

public class AusstattungDB implements GenericDB<Ausstattung>{
	
	private Connection connection;

	private AusstattungMapper ausstattungMapper = new AusstattungMapper();

	private static final String INSERT_AUSSTATTUNG = "INSERT INTO public.ausstattung(beschreibung, gebucht, raum_id) VALUES (?, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.ausstattung;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.ausstattung WHERE id_ausstattung = ?";
	private static final String UPDATE_AUSSTATTUNG = "UPDATE public.ausstattung SET beschreibung = ?, gebucht = ? , raum_id = ? WHERE id_ausstattung = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.ausstattung WHERE id_ausstattung = ?";

	public AusstattungDB(final Connection connection) {
		this.connection = connection;
	}
	

	@Override
	public int saveItem(Ausstattung item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUSSTATTUNG);
		preparedStatement.setString(1, item.getBeschreibung());
		preparedStatement.setBoolean(2, item.isGebucht());
		preparedStatement.setInt(3, item.getRaum_id());

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	@Override
	public List<Ausstattung> findAllItems() throws SQLException {
		List<Ausstattung> ausstattungen = new ArrayList<Ausstattung>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Ausstattung ausstattung = ausstattungMapper.rowMapper(resultSet);
			ausstattungen.add(ausstattung);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return ausstattungen;
	}

	@Override
	public Ausstattung findById(int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return ausstattungMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Ausstattung();
	}

	@Override
	public int updateItem(Ausstattung item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUSSTATTUNG);
		preparedStatement.setString(1, item.getBeschreibung());
		preparedStatement.setBoolean(2, item.isGebucht());
		preparedStatement.setInt(3, item.getRaum_id());
		preparedStatement.setInt(4, item.getId_ausstattung());
		
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
