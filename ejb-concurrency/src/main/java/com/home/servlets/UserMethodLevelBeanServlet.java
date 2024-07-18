package com.home.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.home.service.UserMethodelLevelService;

/**
 * Servlet implementation class UserMethodLevelBean
 */
@WebServlet("/userServlet")
public class UserMethodLevelBeanServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6429841607245825614L;
	
	@EJB
    private UserMethodelLevelService userBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        // Ruft die getUserCount() Methode auf
        int userCount = userBean.getTotalUsers();
        
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Benutzeranzahl: " + userCount + "</h1>");
        response.getWriter().println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ruft die addUser() Methode auf
        userBean.registerNewUser();
        
        response.sendRedirect(request.getContextPath() + "/userServlet");
    }
}