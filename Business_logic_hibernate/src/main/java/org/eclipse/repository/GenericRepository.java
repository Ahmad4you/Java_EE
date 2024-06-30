package org.eclipse.repository;

import java.util.List;

/**
 * CrudRepository
 * @param <T>
 */

public interface GenericRepository<T> {

	int add(T entity) throws Exception;
	int update(T entity) throws Exception;
	void delete(T entity) throws Exception;
	List<T> findAll() throws Exception;
	T findById(int id) throws Exception;
	T findByName(String name) throws Exception;
	
}