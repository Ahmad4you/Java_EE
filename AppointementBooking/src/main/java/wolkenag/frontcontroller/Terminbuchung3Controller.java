package wolkenag.frontcontroller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Terminbuchung3Controller
 */
public class Terminbuchung3Controller extends HttpServlet implements Controller{
	private static final long serialVersionUID = 1L;
	
	final static Logger LOGGER = LogManager.getLogger();
	private static final String Terminbuchung_JSP = "/WEB-INF/pages/main/terminbuchung3.jsp";
	
	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(Terminbuchung_JSP);
		return resolver;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Terminbuchung3Controller() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
