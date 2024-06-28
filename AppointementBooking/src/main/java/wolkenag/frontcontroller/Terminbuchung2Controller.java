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
import jakarta.servlet.http.HttpSession;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.AusstattungDB;
import wolkenag.db.dao.impl.BuchungDB;
import wolkenag.db.dao.impl.RaumbuchungDB;
import wolkenag.domain.Ausstattung;
import wolkenag.domain.Buchung;
import wolkenag.domain.Raumbuchung;

/**
 * Servlet implementation class Terminbuchung2Controller
 */
public class Terminbuchung2Controller extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LogManager.getLogger();
	private static final String Terminbuchung_JSP = "/WEB-INF/pages/main/terminbuchung2.jsp";

	Connection connection = null;

	boolean billardtisch;
	boolean tischkicker;
	boolean dartscheibe;
	boolean catering;
	String extraWuensche;
	HttpSession session;
	int id_Mitarbeiter;
	int id_Buchung;
	String radio_btn = "";

	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(Terminbuchung_JSP);
		return resolver;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Terminbuchung2Controller() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void CheckInput(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		System.out.println("Terminbuchung2 ++CheckInput()");

		session = request.getSession(false);
		if (session.getAttribute("id") != null) {

			id_Mitarbeiter = (int) session.getAttribute("id");
			System.out.println("id_Mitarbeiter: " + id_Mitarbeiter);
		}
		if (session.getAttribute("id_Buchung") != null) {

			id_Buchung = (int) session.getAttribute("id_Buchung");
			System.out.println("id_Buchung: " + id_Buchung);
		}

		try {

			String log = request.getParameter("btn_weiter");
			if (log == null) {
				return;
			}
			radio_btn = request.getParameter("rad");

			billardtisch = request.getParameter("billardtischID") != null;
			tischkicker = request.getParameter("tischkickerID") != null;
			dartscheibe = request.getParameter("dartscheibeID") != null;
			catering = request.getParameter("cateringID") != null;
			extraWuensche = request.getParameter("extraWuenscheID");

			DatabaseConnection conn = new DatabaseConnection();
			connection = conn.getConnection();

			if (conn != null) {
				BuchungDB buchungDB = new BuchungDB(connection);
				AusstattungDB ausstattungDB = new AusstattungDB(connection);

				System.out.println("radio_btn: " + radio_btn);
				System.out.println("Ausstattung buchen");

				if (radio_btn != null) {

					if (radio_btn.equals("ulm_checked")) {
						if (billardtisch == true) {
							ausstattungDB.saveItem(new Ausstattung("Billardtisch", true, 1));
						}
						if (tischkicker == true) {
							ausstattungDB.saveItem(new Ausstattung("Tischkicker", true, 1));
						}
						if (dartscheibe == true) {
							ausstattungDB.saveItem(new Ausstattung("Dartscheibe", true, 1));
						}
					}

					if (radio_btn.equals("greven_checked")) {
						if (billardtisch == true) {
							ausstattungDB.saveItem(new Ausstattung("Billardtisch", true, 2));
						}
						if (tischkicker == true) {
							ausstattungDB.saveItem(new Ausstattung("Tischkicker", true, 2));
						}
						if (dartscheibe == true) {
							ausstattungDB.saveItem(new Ausstattung("Dartscheibe", true, 2));
						}
					}
				}
				System.out.println("Buchung updated: "
						+ buchungDB.updateTerminbuchung2(new Buchung(catering, extraWuensche, true, id_Buchung)));
				raumbuchen();

				response.sendRedirect("/AppointementBooking/car?controller=terminbuchung3");

			}

			DatabaseConnection.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}

	}

	public void raumbuchen() throws SQLException {
		RaumbuchungDB raumbuchungDB = new RaumbuchungDB(connection);

		if (id_Buchung != 0) {
			System.out.println("id_Buchung: " + id_Buchung);
			System.out.println(
					"RaumbuchungDB Zuordnung Inserted: " + raumbuchungDB.saveItem(new Raumbuchung(id_Buchung, 1)));

		}
	}

}
