/**
 * 
 */
package com.home.repository;

import java.util.List;

import com.home.model.User;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface UserService extends GenericService<User>{
	void create(User user);
    public User findById(Long id);
	public User update(Long id, User user);
	public boolean delete(Long id);
	List<User> findAll();

	User findByField(String fieldName, Object fieldValue);
	boolean userExists(String firstName, String lastName);
}
