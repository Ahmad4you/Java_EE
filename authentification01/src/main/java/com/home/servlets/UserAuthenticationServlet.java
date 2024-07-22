package com.home.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.CallerOnlyCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class UserAuthentificationServlet
 */
@DeclareRoles({"role1", "role2", "role3"})
@WebServlet(name = "/UserAuthenticationServlet", urlPatterns = {"/UserAuthenticationServlet"})
public class UserAuthenticationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;
    
    /*
     * Sie authentifiziert den Benutzer basierend auf dem "name" Parameter.
     * Sie gibt Informationen über den authentifizierten Benutzer zurück, einschließlich des Benutzernamens,
     *  der Rollenzugehörigkeit und des Zugriffs auf bestimmte Ressourcen.
     *  
     *  Ein Benutzer sendet eine GET-Anfrage an "/UserAuthenticationServlet?name=user".
     *  Das Servlet ruft securityContext.authenticate() auf, was den AuthenticationMechanism aktiviert.
     *  Wenn die Authentifizierung erfolgreich ist (d.h. der Name ist "user"), werden dem Benutzer die Rollen "role1" und "role2" zugewiesen.
     *  Das Servlet gibt dann Informationen über den authentifizierten Benutzer zurück, einschließlich seiner Rollen und Zugriffsrechte.
     *  
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        if (null != name || !"".equals(name)) {
            AuthenticationStatus status = securityContext.authenticate(
                    request, response, AuthenticationParameters.withParams().credential(new CallerOnlyCredential(name)));

            response.getWriter().write("Authentication status: " + status.name() + "\n");
        }

        String principal = null;
        if (request.getUserPrincipal() != null) {
            principal = request.getUserPrincipal().getName();
        }

        response.getWriter().write("User: " + principal + "\n");
        response.getWriter().write("Role \"role1\" access: " + request.isUserInRole("role1") + "\n");
        response.getWriter().write("Role \"role2\" access: " + request.isUserInRole("role2") + "\n");
        response.getWriter().write("Role \"role3\" access: " + request.isUserInRole("role3") + "\n");
        response.getWriter().write("Access to /authServlet? " + securityContext.hasAccessToWebResource("/authServlet") + "\n");
    }
}
