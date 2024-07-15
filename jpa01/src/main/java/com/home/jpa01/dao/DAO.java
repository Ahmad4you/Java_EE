/**
 * 
 */
package com.home.jpa01.dao;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface DAO<T> {
	public T create(T t);
	public T read(Class<T> classType, Long id);
	public T update(Class<T> classType, Long id, T t);
	public boolean delete(Class<T> classType, Long id);

}
