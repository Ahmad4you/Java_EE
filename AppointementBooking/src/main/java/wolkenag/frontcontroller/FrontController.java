package wolkenag.frontcontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.MitarbeiterDB;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/car")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTROLLER_NAME = "controller";
	Connection connection = null;
	final static Logger LOGGER = LogManager.getLogger();
	String email ="";
	String passwort ="";
	public int id_mitarbeiter;
	 HttpSession session;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		
		
		doGet(request, response);

	}
	public void CheckLogin(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Index ++CheckLogin()");
		session=request.getSession();  
		
		email = request.getParameter("email");
		passwort = request.getParameter("password");
		System.out.println("email: "+email);
		
		if (email == "" || passwort == "") {
			System.out.println("null wert");
			return;
		}
		
		
		try {
			
			String log = request.getParameter("Login");
			if (log == null ) {
				return;
			}
			
			DatabaseConnection conn = new DatabaseConnection();
			connection = conn.getConnection();

			if (conn != null) {
				MitarbeiterDB mitarbeiterDB = new MitarbeiterDB(connection);
				

					if (email != null && passwort != null) {
						
						System.out.println("login Anmeldung");
						System.out.println("findIdByEmail: " + mitarbeiterDB.findIdByEmail(email));
						id_mitarbeiter = mitarbeiterDB.findIdByEmail(email).getId_mitarbeiter();
						System.out.println("getId_mitarbeiter: " + id_mitarbeiter);
					if (email.equals(mitarbeiterDB.findIdByEmail(email).getEmail()) && passwort.equals(mitarbeiterDB.findIdByEmail(email).getPasswort())) {
						response.sendRedirect("/AppointementBooking/car?controller=startseite");
						session.setAttribute("id",id_mitarbeiter);  
					}

				}
			
			varNullwert();
			DatabaseConnection.closeConnection(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}

		Object event= request.getParameter("send");
		System.out.println("event from js: " +event);
	}

	private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		String controllerName = request.getParameter(CONTROLLER_NAME);

		ControllerFactory factory = new ControllerFactory();
		Controller controller = factory.getController(controllerName);
		ViewResolver resolver = controller.resolve(request, response);
		dispatch(request, response, resolver);
	}

	private void dispatch(final HttpServletRequest request, final HttpServletResponse response,
			final ViewResolver resolver) throws ServletException, IOException {

		String view = resolver.getView();
		switch (resolver.getResolveAction()) {
		case FORWARD:
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
			dispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(view);
			break;

		default:
			break;
		}
	}

	public void varNullwert() {
		email = "";
		passwort = "";
	}

}
