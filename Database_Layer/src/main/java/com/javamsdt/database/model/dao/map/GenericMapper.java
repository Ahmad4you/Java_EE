package com.javamsdt.database.model.dao.map;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericMapper<T> {
	/*
	 * Service layer
	 */


	T rowMapper(ResultSet resultSet) throws SQLException;
}
