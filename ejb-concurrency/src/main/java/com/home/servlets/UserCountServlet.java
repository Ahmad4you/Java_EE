package com.home.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.home.selfManagedBean.UserSelfManagedBean;

/**
 * Servlet implementation class UserCountServlet
 */
@WebServlet("/userCount")
public class UserCountServlet extends HttpServlet {

    /**
	 * Um die Methoden der UserSelfManagedBean aus einem Servlet aufzurufen, 
	 * müssen zunächst eine Instanz der Bean erhalten. Da es sich um eine EJB (Enterprise JavaBean) handelt, 
	 * können diese mittels JNDI (Java Naming and Directory Interface) oder Dependency Injection abrufen, 
	 * abhängig von Serverumgebung und Konfiguration.
	 * 
	 * 
	 */
	private static final long serialVersionUID = 7972264709922094113L;
	private UserSelfManagedBean userBean;
	
//	@Inject
//    private UserSelfManagedBean userBean;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            //  JNDI-Name für WildFly und Jakarta EE
            userBean = (UserSelfManagedBean) context.lookup("java:module/UserSelfManagedBean");
        } catch (NamingException e) {
            throw new ServletException("Could not initialize UserSelfManagedBean", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userBean.addUser();
        int count = userBean.getUserCount();
        response.getWriter().println("Current user count: " + count);
    }
}