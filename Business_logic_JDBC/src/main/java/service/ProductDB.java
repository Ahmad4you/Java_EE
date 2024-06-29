package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import db.config.DatabaseConn;
import domain.Products;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductDB implements MenuModule<Products>{
	
	private Connection connection;

	private Products categories = new Products();

	private static final String INSERT = "INSERT INTO primefaces.products(name, description) VALUES (?, ?);";
	private static final String SELECT_ALL = "SELECT * FROM primefaces.products;";
	private static final String SELECT_BY_ID = "SELECT * FROM primefaces.products WHERE id = ?";
	private static final String UPDATE_AUSSTATTUNG = "UPDATE primefaces.products SET name = ?, description = ? ";
	private static final String DELETE_BY_ID = "DELETE FROM primefaces.products WHERE id = ?";

	public ProductDB(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public int addTyp(Products products) throws Exception {
		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setString(1, products.getName());
		preparedStatement.setString(2, products.getDescription());
		

		insertedRecord = preparedStatement.executeUpdate();
		DatabaseConn.closeStatement(preparedStatement);

		return insertedRecord;
	}

	@Override
	public int editTyp(Products products) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteTyp(Products products) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Products> findAllTyps() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products findTyp(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> searchTypByName(String ProductName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
