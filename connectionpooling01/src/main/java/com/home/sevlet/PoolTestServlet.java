package com.home.sevlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.home.connpolling.SysConfigBean;



/**
 * Servlet implementation class PoolTestServlet
 */
@WebServlet(name = "PoolTestServlet", urlPatterns = {"/PoolTestServlet"})
public class PoolTestServlet extends HttpServlet {

    private static final long serialVersionUID = 5072227196416403652L;
	@EJB
    private SysConfigBean config;

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try (PrintWriter writer = response.getWriter()) {
////            config = new SysConfigBean();
//            writer.write(config.getSysConfig());
//        } catch (SQLException | NamingException ex) {
//            System.err.println(ex.getMessage());
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
	
//	 @Override
//	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	            throws ServletException, IOException {
//	        response.setContentType("application/json");
//	        try (PrintWriter writer = response.getWriter()) {
//	            writer.write(config.getSysConfig());
//	        } catch (SQLException | NamingException ex) {
//	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	            response.getWriter().write("Database error: " + ex.getMessage());
//	        } catch (Exception ex) {
//	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	            response.getWriter().write("Unexpected error: " + ex.getMessage());
//	        }
//	    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            String json = config.getSysConfig();
            response.getWriter().write(json);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }
	
	
	
}
