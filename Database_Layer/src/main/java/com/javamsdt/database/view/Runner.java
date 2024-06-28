package com.javamsdt.database.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javamsdt.database.domain.User;
import com.javamsdt.database.model.config.DatabaseConnection;
import com.javamsdt.database.model.dao.impl.UserDao;

public class Runner {
	public static void main(final String[] args) throws SQLException, ClassNotFoundException {

		Connection connection = DatabaseConnection.getConnection();
		
//		String createUserTable = "CREATE TABLE users ( " + "id SERIAL NOT NULL PRIMARY KEY, "
//				+ "username VARCHAR(255), " + "password VARCHAR(255), " + "enabled BOOLEAN DEFAULT true )";
//		Statement statement = connection.createStatement();
//		statement.execute(createUserTable);

		User userOne = new User("userOne", "passwordOne");
		User userTwo = new User("userTwo", "passwordThree");
		User userThree = new User("userThree", "passwordThree");
		User userFour = new User("userFour", "passwordFour");

		// ====================== USER DAO OPERATIONS ====================== //

		UserDao userDao = new UserDao(connection);

		// Create
		System.out.println("====================== Create ======================");
		System.out.println("Inserted: " + userDao.saveItem(userOne));
		System.out.println("Inserted: " + userDao.saveItem(userTwo));
		System.out.println("Inserted: " + userDao.saveItem(userThree));
		System.out.println("Inserted: " + userDao.saveItem(userFour));

		// Read
		System.out.println("====================== Read ======================");
		List<User> users = userDao.findAllItems();
		for (User user : users) {
			System.out.println(user);
		}
		System.out.println("====================== REad By Id ======================");

		System.out.println(userDao.findById(2));

		// Update
		System.out.println("====================== Update ======================");

//		User userOneUpdated = new User(1, "userOneUpdated", "passwordOneUpdated", true);
//		System.out.println("Updated: " + userDao.updateItem(userOneUpdated));
//		System.out.println("userOneUpdated: " + userDao.findById(1));
//
//		// delete
//		System.out.println("====================== Delete ======================");
//		System.out.println("Deleted: " + userDao.deleteById(4));
//
//		users = userDao.findAllItems();
//		for (User user : users) {
//			System.out.println(user);
//		}

		DatabaseConnection.closeConnection(connection);
	}
}
