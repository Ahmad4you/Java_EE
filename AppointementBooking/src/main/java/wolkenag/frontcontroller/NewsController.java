package wolkenag.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wolkenag.domain.Buchung;


public class NewsController extends HttpServlet implements Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NEWS_JSP = "/WEB-INF/pages/main/news.jsp";

	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();
		
		resolver.forward(NEWS_JSP);
		return resolver;
	}
	

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		 Terminbuchung1Controller tb = new Terminbuchung1Controller();
		  Gson gson = new Gson();
		 
		 try {
			List<Buchung> events = new ArrayList<Buchung>();
			events = tb.getEvents();
			events.add((Buchung) tb.getEvents());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
	        String eventsJsonString = gson.toJson("Hallo");

	        PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.append(eventsJsonString);
	        out.append("123");
	        out.flush();   
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			doGet(request, response);
		}
	        
	      
	   
}
