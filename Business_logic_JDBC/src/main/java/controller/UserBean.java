package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.CategoriesDB;
import service.CategoriesService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import db.config.DatabaseConn;
import domain.Categories;

@Named
@RequestScoped
public class UserBean {

/**
 * 
 * direkte JDBC Transaktion
 * @return
 */
	
	public List<Categories> getUsers() {
	    try (Connection connection = DatabaseConn.getConnection()) {
	        CategoriesDB userService = new CategoriesDB(connection);
	        return userService.findAllTyps();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}
	
	public void someTransactionalMethod() throws SQLException {
	    Connection conn = null;
	    try {
	        conn = DatabaseConn.getConnection();
	        conn.setAutoCommit(false);

	        // Führen Sie hier Ihre Datenbankoperationen durch

	        conn.commit();
	    } catch (SQLException e) {
	        if (conn != null) {
	            conn.rollback();
	        }
	        throw e;
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}
	
	/**
	 * 
	 * Hibernate Transaktion
	 */
	    
//	    @Inject
//	    private CategoriesService categoriesService;
//
//	    private List<Categories> users;
//
//	    public List<Categories> getUsers() {
//	        if (users == null) {
//	            try {
//	                users = categoriesService.findAllTyps();
//	                if(users.isEmpty()) {
//	                    System.out.println("Die Liste ist leer");
//	                }
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        return users;
//	    }
	    
	    public void init(){
	    	System.out.println("init()....");
	    }
}
