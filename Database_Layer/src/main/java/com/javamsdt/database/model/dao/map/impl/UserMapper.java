package com.javamsdt.database.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.database.domain.User;
import com.javamsdt.database.model.dao.map.GenericMapper;

public class UserMapper implements GenericMapper<User>{
	
	/*
	 * Service layer
	 */

	public User rowMapper(final ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		boolean enabled = resultSet.getBoolean("enabled");

		return new User(id, username, password, enabled);
	}

}
