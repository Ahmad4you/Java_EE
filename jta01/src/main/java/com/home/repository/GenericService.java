/**
 * 
 */
package com.home.repository;

import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface GenericService<T> { // DAO
    
    void create(T t);
	public T findById(Long id);
	public T update(Long id, T t);
	public boolean delete(Long id);
    List<T> findAll();

	T findByField(String fieldName, Object fieldValue);
}
