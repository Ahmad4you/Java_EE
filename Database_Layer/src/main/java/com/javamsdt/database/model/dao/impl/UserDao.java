package com.javamsdt.database.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamsdt.database.domain.User;
import com.javamsdt.database.model.config.DatabaseConnection;
import com.javamsdt.database.model.dao.api.GenericDao;
import com.javamsdt.database.model.dao.map.impl.UserMapper;

public class UserDao implements GenericDao<User> {
	/*
	 * DB layer
	 */

	private Connection connection;

	private UserMapper userMapper = new UserMapper();

	private static final String INSERT_USER = "INSERT INTO users ( username, password ) VALUES (?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM users";
	private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, "
			+ "enabled = ? WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

	public UserDao(final Connection connection) {
		this.connection = connection;
	}

	public int saveItem(final User item) throws SQLException {

		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
		preparedStatement.setString(1, item.getUsername());
		preparedStatement.setString(2, item.getPassword());

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	public List<User> findAllItems() throws SQLException {

		List<User> users = new ArrayList<User>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = userMapper.rowMapper(resultSet);
			users.add(user);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return users;
	}

	public User findById(final int id) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return userMapper.rowMapper(resultSet);
		}

		DatabaseConnection.closeResultset(resultSet);
		DatabaseConnection.closeStatement(preparedStatement);

		return new User();
	}

	public int updateItem(final User item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
		preparedStatement.setString(1, item.getUsername());
		preparedStatement.setString(2, item.getPassword());
		preparedStatement.setBoolean(3, item.isEnabled());
		preparedStatement.setInt(4, item.getId());

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
