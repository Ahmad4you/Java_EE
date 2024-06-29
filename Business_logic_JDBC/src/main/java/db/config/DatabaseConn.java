package db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConn {
	
	final static Logger LOGGER = LogManager.getLogger();

	private final static String URL = "jdbc:mysql://localhost:3306/primefaces";
	private final static String USER = "root";
	private final static String PASSWORD = "";
	static Connection connection = null;
	
		

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
			LOGGER.info("connection to postgresDB Successfully!!!!!!!");
		} catch (SQLException e) {
			
			LOGGER.error("SQLException..Unable to connect to Database URL: " + URL + " " + e);
			
		} catch (ClassNotFoundException e) {
			
			LOGGER.error("ClassNotFoundException..Unable to connect to Database URL: " + URL + " " + e);
		}
		
		return connection;
		
	}

	public static void closeStatement(final Statement statement) {
		try {
			statement.close();
			LOGGER.info("Statment closed Successfully");
		} catch (SQLException e) {
			
			LOGGER.error("SQLException.. " + e);
		}
	}

	public static void closeResultset(final ResultSet resultSet) {
		try {
			resultSet.close();
			LOGGER.info("Statment closed Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}
	}

	public static void closeConnection(final Connection connection) {
		try {
			connection.close();
			LOGGER.info("Statment closed Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}
	}

}
