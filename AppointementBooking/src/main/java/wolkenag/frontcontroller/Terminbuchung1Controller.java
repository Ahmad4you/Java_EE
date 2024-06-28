package wolkenag.frontcontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wolkenag.db.config.DatabaseConnection;
import wolkenag.db.dao.impl.BuchungDB;
import wolkenag.db.dao.impl.TeilnehmerDB;
import wolkenag.domain.Buchung;
import wolkenag.domain.Teilnehmer;

/**
 * Servlet implementation class Terminbuchung1Controller
 */

public class Terminbuchung1Controller extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LogManager.getLogger();
	private static final String Terminbuchung_JSP = "/WEB-INF/pages/main/terminbuchung1.jsp";

	Connection connection = null;
	String title ="";
	String grund ="";
	String von ="";
	String bis = "";
	HttpSession session;
	int id_Mitarbeiter;
	int id_Buchung;
	String calendarDate= ""; 

	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(Terminbuchung_JSP);
		return resolver;
	}

	public Terminbuchung1Controller() {
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

	public void CheckInput(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Terminbuchung1 ++CheckInput()");
		session = request.getSession(false);
		if (session.getAttribute("id") != null) {

			id_Mitarbeiter = (int) session.getAttribute("id");
			System.out.println("id_Mitarbeiter: " + id_Mitarbeiter);
		}

		try {

			String log = request.getParameter("nschrit");
			if (log == null) {
				return;
			}
 
			
			calendarDate = (String) request.getSession().getAttribute("date");
			if(calendarDate == null) {
				 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				 calendarDate = sdf2.format(timestamp) + " ";
			        System.out.println("now() ++" + calendarDate);      
			}
			title = request.getParameter("title");
			grund = request.getParameter("grund");
			String vonString  = request.getParameter("von");
//			von = vonString.substring(0, vonString.length() - 6) + " " + vonString.substring(11, vonString.length()) + ":00";
			von = calendarDate +vonString +  ":00";
			String bisString  = request.getParameter("bis");
//			bis = bisString.substring(0, bisString.length() - 6) + " " + bisString.substring(11, bisString.length()) + ":00";
			bis = calendarDate +bisString +  ":00";
			System.out.println("name: " + title);
			System.out.println("von: " + von);

			DatabaseConnection conn = new DatabaseConnection();
			connection = conn.getConnection();

			if (conn != null) {
				BuchungDB buchungDB = new BuchungDB(connection);

				if (title != null && grund != null && von != null && bis != null) {

					System.out.println("Zeit Buchung");
					System.out.println("Buchung Inserted: " + buchungDB.saveItem(
							new Buchung(title, grund, Timestamp.valueOf(von), Timestamp.valueOf(bis))));

					addTeilnehmer();

					response.sendRedirect("/AppointementBooking/car?controller=terminbuchung2");

				}
			}

			DatabaseConnection.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("SQLException.. " + e);
		}

	}

	public void addTeilnehmer() throws SQLException {
		BuchungDB buchungDB = new BuchungDB(connection);
		TeilnehmerDB teilnehmerDB = new TeilnehmerDB(connection);
		id_Buchung = buchungDB.findIdByTitel(title, Timestamp.valueOf(von), Timestamp.valueOf(bis)).getId_buchung();
		if (id_Buchung != 0) {
			session.setAttribute("id_Buchung",id_Buchung);
			System.out.println("id_Buchung: " + id_Buchung);
			System.out.println(
					"Zuordnung Inserted: " + teilnehmerDB.saveItem(new Teilnehmer(id_Buchung, id_Mitarbeiter, id_Mitarbeiter)));
			title = "";
		}
	}
	
	public List<Buchung> getEvents() throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		BuchungDB buchungDB = new BuchungDB(connection);
		List<Buchung> events = buchungDB.findAllevevts();
			
		return events;
		}

}
