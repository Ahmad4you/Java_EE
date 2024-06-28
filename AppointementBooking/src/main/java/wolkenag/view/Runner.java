package wolkenag.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.BuchungDB;
import wolkenag.db.dao.impl.MitarbeiterDB;
import wolkenag.domain.Buchung;
import wolkenag.domain.Mitarbeiter;

public class Runner {
	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(final String[] args) throws SQLException {
//		final Logger LOGGER = LogManager.getLogger(getClass().getName());
		final Logger logger = LoggerFactory.getLogger(Class.class); 
		
		Connection connection = null;	
		try {
		DatabaseConnection conn = new DatabaseConnection();
	    connection = conn.getConnection();
//			connection = DatabaseConnection.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("SQLException..Unable to connect to Database " + e1.getMessage());
			
           
		}

//		String createMitarbeiterTable = "CREATE TABLE Mitarbeiters ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
//				+ "Mitarbeitername VARCHAR(255), " + "password VARCHAR(255), " + "enabled BOOLEAN DEFAULT true )";
//		Statement statement = connection.createStatement();
//		statement.execute(createMitarbeiterTable);


//		Mitarbeiter MitarbeiterOne = new Mitarbeiter("Alrefai", "Ahmad", "testemail@outlook.de", "1234");
//		Mitarbeiter MitarbeiterTwo = new Mitarbeiter("Gebhardt", "tobi", "testemail2@outlook.de", "2345");

		// ====================== Mitarbeiter DAO OPERATIONS ====================== //

		MitarbeiterDB mitarbeiterDB = new MitarbeiterDB(connection);

		// Create
		System.out.println("====================== Create Tabelle Mitarbeiter ======================");
//		System.out.println("Inserted: " + mitarbeiterDB.saveItem(MitarbeiterOne));
//		System.out.println("Inserted: " + mitarbeiterDB.saveItem(MitarbeiterTwo));
		

		// Read
		System.out.println("====================== Read ======================");
		List<Mitarbeiter> Mitarbeiters = null;
		try {
			
			Mitarbeiters = mitarbeiterDB.findAllItems();

			if(Mitarbeiters != null){ 
				for (Mitarbeiter Mitarbeiter : Mitarbeiters) {
					System.out.println(Mitarbeiter);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("====================== REad By Id ======================");
		
		MitarbeiterDB mitarbeiterDB2 = new MitarbeiterDB(connection);
		try {
			System.out.println("findById: "+mitarbeiterDB2.findById(2));
			System.out.println("findIdByName: "+mitarbeiterDB2.findIdByName("Bauer").getId_mitarbeiter());
		} catch (SQLException e) {
			throw new RuntimeException(e);
//			e.printStackTrace();
		}

		// Update
		System.out.println("====================== Update ======================");

//		Mitarbeiter MitarbeiterOneUpdated = new Mitarbeiter(2, "MitarbeiterOneUpdated", "passwordOneUpdated","test22@gmail.com", "467645");
//		System.out.println("Updated: " + mitarbeiterDB.updateItem(MitarbeiterOneUpdated));
		System.out.println("MitarbeiterOneUpdated: " + mitarbeiterDB.findById(2));

		// delete
		System.out.println("====================== Delete ======================");
//		System.out.println("Deleted: " + mitarbeiterDB.deleteById(6));

		Mitarbeiters = mitarbeiterDB.findAllItems();
		for (Mitarbeiter Mitarbeiter : Mitarbeiters) {
			System.out.println(Mitarbeiter);
		}
		System.out.println("====================== Create Tabelle Buchung ======================");
		
		BuchungDB buchungDB = new BuchungDB(connection);
//		Buchung b1 = new Buchung("erste Buchung", "test Beschreibung", Timestamp.valueOf( "2016-06-22 19:10:25"), Timestamp.valueOf( "2016-06-22 20:10:25"), false, "extra", false);
//		Buchung b2 = new Buchung("zweite Buchung", "test2 Beschreibung", Timestamp.valueOf( "2016-06-23 19:10:25"), Timestamp.valueOf( "2016-06-23 20:10:25"), false, "extra2", false);
		
		
//		System.out.println("Inserted: " + buchungDB.saveItem(b1));
//		System.out.println("Inserted: " + buchungDB.saveItem(b2));
		
		System.out.println("====================== Read ======================");
		List<Buchung> Buchungen = null;
		try {
			
			Buchungen = buchungDB.findAllItems();
			if(Buchungen != null){
				for (Buchung b : Buchungen) {
					System.out.println(b);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("====================== Update ======================");
		//Buchung b3 = new Buchung(2, "test2 Beschreibung", Timestamp.valueOf( "2017-06-22 19:10:25"), Timestamp.valueOf( "2017-06-22 20:10:25"));
//		System.out.println("Updated: " + buchungDB.updateItem(b3));
		System.out.println("Updated value: " + buchungDB.findById(2));
		
		DatabaseConnection.closeConnection(connection);
	}
}
