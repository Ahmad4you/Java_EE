package com.javamsdt.servlettest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        
    }

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		javax.servlet.RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//		dispatcher.forward(req, resp);
		
		resp.getWriter().append("Served at: Hello servlet doget()").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	@Override
	public void init() throws ServletException {
		System.out.println("initialising hello servleet");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy methode");
	}

}
