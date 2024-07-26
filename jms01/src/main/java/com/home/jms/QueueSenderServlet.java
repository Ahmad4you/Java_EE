package com.home.jms;

import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class QueueSenderServlet
 */
@WebServlet(name = "QueueSenderServlet", urlPatterns = {"/QueueSenderServlet"})
public class QueueSenderServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6817532093680257553L;
	@Inject
    private QueueSender sender;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter writer = response.getWriter()){
            sender.send();
            writer.write("Message sent to queue. Check the log for details.");
        } catch (JMSException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
