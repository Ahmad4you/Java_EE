package com.javamsdt.database.model.dao.api;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
	/*
	 * DB layer CRUD
	 */

	
	int saveItem(T item) throws SQLException;     // creat

	List<T> findAllItems() throws SQLException;   // read

	T findById(int id) throws SQLException;       //read

	int updateItem(T item) throws SQLException;   // update

	int deleteById(int id) throws SQLException;   // delete
}
