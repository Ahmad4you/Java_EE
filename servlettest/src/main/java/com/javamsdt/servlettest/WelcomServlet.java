package com.javamsdt.servlettest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcom.jsp")
public class WelcomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter outWriter = resp.getWriter();
		outWriter.println("<head>");
		outWriter.println("</head>");
		outWriter.println("<body>");
		outWriter.println("<h1> Testing new servlet </h1>");
		outWriter.println("<p> i am a paragraph </p>");
		outWriter.println("</body>");

		javax.servlet.RequestDispatcher dispatcher = req.getRequestDispatcher("welcom.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
}
