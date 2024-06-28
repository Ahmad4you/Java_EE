package wolkenag.view;

import java.sql.Connection;
import java.sql.SQLException;

import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.MitarbeiterDB;

public class Runner2 {

	public static void main(String[] args) {
		Connection connection;
		

	System.out.println("====================== REad By Id ======================");
		
		
		try {
			DatabaseConnection conn = new DatabaseConnection();
		    connection = conn.getConnection();
			MitarbeiterDB mitarbeiterDB2 = new MitarbeiterDB(connection);
			System.out.println(mitarbeiterDB2.findById(2));
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
