package com.javamsdt.database.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		String url = "jdbc:postgresql://localhost:5432/w_lax";
		String user = "postgres";
		String password = "postgres";
//		String dbDriver = "org.postgresql.Driver ";
//		Class.forName(dbDriver);
		
		Connection connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}

	public static void closeStatement(final Statement statement) {
		try {
			statement.close();
			System.out.println("Statment closed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultset(final ResultSet resultSet) {
		try {
			resultSet.close();
			System.out.println("Resultset closed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(final Connection connection) {
		try {
			connection.close();
			System.out.println("Connection closed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
