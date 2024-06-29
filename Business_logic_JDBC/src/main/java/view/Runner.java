package view;

import java.sql.Connection;
import org.apache.logging.log4j.*;

import db.config.DatabaseConn;
import domain.Categories;
import service.CategoriesDB;

public class Runner {

	public static void main(String[] args) {
		
		final Logger logger = LogManager.getLogger();
		int NumOfAffectedRows = 0;
		
		Connection connection = null;	
		try {
		DatabaseConn conn = new DatabaseConn();
	    connection = conn.getConnection();
	    
	    CategoriesDB categoriesDB= new CategoriesDB(connection);
		NumOfAffectedRows=categoriesDB.addTyp(new Categories("jdbc2 projekt", "some Text"));
		logger.fatal("Num Of Affected Rows: "+ NumOfAffectedRows);

		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("SQLException..Unable to connect to Database " + e1.getMessage());
			
		}
		

	}

}
