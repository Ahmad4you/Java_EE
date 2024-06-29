package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.config.DatabaseConn;
import domain.Categories;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

//@ApplicationScoped
public class CategoriesDB implements MenuModule<Categories>{
	private Connection connection;
	
//	@Resource(lookup = "java:comp/env/jdbc/YourDataSource")
//	private DatabaseConn conn;
	
//	@Resource(lookup = "java:comp/env/jdbc/ahmadmysql")
	private DatabaseConn conn;

	private Categories categories = new Categories();

	private static final String INSERT = "INSERT INTO primefaces.categories(name, description) VALUES (?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM primefaces.categories;";
	private static final String SELECT_BY_ID = "SELECT * FROM primefaces.categories WHERE id = ?";
	private static final String UPDATE_AUSSTATTUNG = "UPDATE primefaces.categories SET name = ?, description = ? ";
	private static final String DELETE_BY_ID = "DELETE FROM primefaces.categories WHERE id = ?";

	public CategoriesDB(final Connection connection) {
		this.connection = connection;
	}
	
	public CategoriesDB() {
		
	}

	@Override
	public int addTyp(Categories item) throws Exception {
		
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setString(2, item.getDescription());
		

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConn.closeStatement(preparedStatement);
		DatabaseConn.closeConnection(connection);

		return insertedRecord;
	}

	@Override
	public int editTyp(Categories products) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteTyp(Categories products) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public List<Categories> findAllTyps() throws Exception {
	    List<Categories> allCategories = new ArrayList<>();

	    try (Connection connection = DatabaseConn.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet result = statement.executeQuery(SELECT_ALL)) {

	        while (result.next()) {
	            Categories currentCategorie = new Categories();
	            currentCategorie.setId(result.getInt("id"));
	            currentCategorie.setName(result.getString("name"));
	            currentCategorie.setDescription(result.getString("description"));
	            allCategories.add(currentCategorie);
	        }
	    }
	    return allCategories;
	}
	
	
	@Override
	public Categories findTyp(int id) throws Exception {
//		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setInt(1, id);
		
		ResultSet result = preparedStatement.executeQuery(SELECT_BY_ID);
		
		if (result.next()) {
			Categories currentCategorie = new Categories();
			currentCategorie.setId(result.getInt("id"));
			currentCategorie.setName(result.getString("name"));
			currentCategorie.setDescription(result.getString("description"));
					
			return currentCategorie;
		}		
		DatabaseConn.closeStatement(preparedStatement);
		DatabaseConn.closeResultset(result);
		DatabaseConn.closeConnection(connection);
		
		return null;
	}

	@Override
	public List<Categories> searchTypByName(String ProductName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
