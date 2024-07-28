/**
 * 
 */
package com.home.repository;

import com.home.model.User;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface UserRepository {
	User findByFirstNameAndLastName(String firstName, String lastName);

	User save(User user);

}
