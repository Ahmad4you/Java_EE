package com.home.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.home.model.User;
import com.home.model.Zugangsdaten;
import com.home.service.UserService;

/**
 * Servlet implementation class UserStatsServlet
 */

@WebServlet("/userStats")
public class UserStatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserStatsServlet() {
        super();
    }
    
    @EJB
    private UserService userService;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		Zugangsdaten zugangsdaten = new Zugangsdaten("ahmad@outlook.de", "asf$%&5467");
    		User user = new User("Ahmad", "meister", 44);
    		user.setZugangsdaten(zugangsdaten);
    		
    		// addieren new User
			userService.registerNewUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
        int totalUsers = userService.getTotalUserCount();
        response.getWriter().println("Total users: " + totalUsers);
    }

}
