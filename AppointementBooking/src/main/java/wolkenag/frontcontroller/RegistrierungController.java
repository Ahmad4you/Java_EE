package wolkenag.frontcontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.MitarbeiterDB;
import wolkenag.domain.Mitarbeiter;

/**
 * Servlet implementation class RegistrierungController
 */

public class RegistrierungController extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;
	final static Logger LOGGER = LogManager.getLogger();
	private static final String Registrierung_JSP = "/WEB-INF/pages/main/registrierung.jsp";
	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(Registrierung_JSP);
		return resolver;
	}
	
	Connection connection = null;
	String nachname= "";
	String vorname ="";
	String email ="";
	String passwort ="";
	String bestaetigung ="";
	public int id_mitarbeiter = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrierungController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void CheckLogin(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RegistrierungController ++CheckLogin()");
		
	
		try {
			
			String submit = request.getParameter("registrieren");
			if (submit == null ) {
				return;
			}
			nachname = request.getParameter("name");
			vorname = request.getParameter("vorname");	
			email = request.getParameter("email");
			passwort = request.getParameter("password");
			bestaetigung = request.getParameter("bestaetigen");
			System.out.println("email: "+email);
			
			if (nachname == ""  || vorname == "" || email == "" 
					|| passwort == "" || bestaetigung == "") {
				System.out.println("null wert");
				return;
			}
			DatabaseConnection conn = new DatabaseConnection();
			connection = conn.getConnection();

			if (conn != null) {
				MitarbeiterDB mitarbeiterDB = new MitarbeiterDB(connection);
				
//				if(passwort != bestaetigung) {
//					System.out.println("passwort muss best�tigt werden");
//					return;
//				}
				
				
					if (nachname != null && vorname != null && email != null && passwort != null && bestaetigung != null) {
						
						System.out.println("sign up Anmeldung");

						System.out.println(
									"Inserted: " + mitarbeiterDB.saveItem(new Mitarbeiter(nachname, vorname, email, passwort)));
							response.sendRedirect("/AppointementBooking/");
						}	
					
				}
			
			varNullwert();
			DatabaseConnection.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}

		Object event= request.getParameter("send");
		System.out.println("event from js: " +event);
	}


	public void varNullwert() {
		nachname = "";
		vorname = "";
		email = "";
		passwort = "";
	}

}
