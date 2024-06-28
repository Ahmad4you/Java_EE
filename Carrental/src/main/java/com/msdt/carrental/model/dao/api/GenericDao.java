package com.msdt.carrental.model.dao.api;

import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface GenericDao<T> {

	// CREATE
	int insertItem(T item) throws DaoException;

	// READ
	List<T> getAllItems() throws DaoException;

	T getItemById(long id) throws DaoException;

	// UPDATE
	int updateItem(T item) throws DaoException;

	// DELETE
	int deleteItem(long id) throws DaoException;

}
