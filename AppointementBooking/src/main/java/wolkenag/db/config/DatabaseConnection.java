package wolkenag.db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnection {
	final static Logger LOGGER = LogManager.getLogger();
	String url = "jdbc:postgresql://localhost:5432/schema";
	String user = "postgres";
	String password = "postgres";
	Connection connection = null;
	
	public Connection getConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
			LOGGER.info("connection to postgresDB Successfully!!!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException..Unable to connect to Database URL: " + url + " " + e);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.error("ClassNotFoundException..Unable to connect to Database URL: " + url + " " + e);
		}
		
		return connection;
		
	}

	public static void closeStatement(final Statement statement) {
		try {
			statement.close();
			LOGGER.info("Statment closed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
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
