package wolkenag.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.api.GenericDB;
import wolkenag.domain.Mitarbeiter;
import wolkenag.model.dao.map.impl.MitarbeiterMapper;

public class MitarbeiterDB implements GenericDB<Mitarbeiter>{

	private Connection connection;

	private MitarbeiterMapper mitarbeiterMapper = new MitarbeiterMapper();

	private static final String INSERT_MITARBEITER = "INSERT INTO public.mitarbeiter(id_mitarbeiter, nachname, vorname, email, passwort) VALUES (DEFAULT, ?, ?, ?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM public.mitarbeiter";
	private static final String SELECT_BY_ID = "SELECT * FROM public.mitarbeiter WHERE id_mitarbeiter = ?";
	private static final String UPDATE_MITARBEITER = "UPDATE public.mitarbeiter SET nachname = ?, vorname = ?, "
			+ "email = ?, passwort = ? WHERE id_mitarbeiter = ?";
	private static final String DELETE_BY_ID = "DELETE FROM public.mitarbeiter WHERE id_mitarbeiter = ?";
	private static final String GET_ID_BY_NAME = "SELECT * FROM public.mitarbeiter WHERE nachname = ?";
	private static final String GET_ID_BY_EMAIL = "SELECT * FROM public.mitarbeiter WHERE email = ?";

	public MitarbeiterDB(final Connection connection) {
		this.connection = connection;
	}

	public int saveItem(final Mitarbeiter item) throws SQLException {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MITARBEITER);
		preparedStatement.setString(1, item.getNachname());
		preparedStatement.setString(2, item.getVorname());
		preparedStatement.setString(3, item.getEmail());
		preparedStatement.setString(4, item.getPasswort());

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	public List<Mitarbeiter> findAllItems() throws SQLException {

		List<Mitarbeiter> mitarbeiters = new ArrayList<Mitarbeiter>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Mitarbeiter mitarbeiter = mitarbeiterMapper.rowMapper(resultSet);
			mitarbeiters.add(mitarbeiter);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return mitarbeiters;
	}

	public Mitarbeiter findById(final int id) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return mitarbeiterMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Mitarbeiter();
	}

	public int updateItem(final Mitarbeiter item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MITARBEITER);
		preparedStatement.setString(1, item.getNachname());
		preparedStatement.setString(2, item.getVorname());
		preparedStatement.setString(3, item.getEmail());
		preparedStatement.setString(4, item.getPasswort());
		preparedStatement.setInt(5, item.getId_mitarbeiter());
		
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
	public Mitarbeiter findIdByName(final String nachname) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_NAME);
		preparedStatement.setString(1, nachname);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return mitarbeiterMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Mitarbeiter();
	}
	
	public Mitarbeiter findIdByEmail(final String email) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_EMAIL);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return mitarbeiterMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new Mitarbeiter();
	}
	
}
