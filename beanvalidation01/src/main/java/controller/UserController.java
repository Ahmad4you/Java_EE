/**
 * 
 */
package controller;


import org.jboss.logging.Logger;
import entity.Benutzer;
import exception.DuplicateEmailException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.BenutzerService;


/**
 * 
 * @author Ahmad Alrefai
 */

@Path("/benutzer")
@RequestScoped
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Inject 
    private BenutzerService benutzerService; 
    
    @PostConstruct
    public void init() {
        LOGGER.info("UserController initialized");
        LOGGER.info("BenutzerService injected: " + (benutzerService != null));
    }

    /* 
     * wenn ein neuer Benutzer erstellt werden soll. es wird aufgerufen, wenn ein HTTP POST-Request an den Endpunkt /benutzer gesendet wird. 
     * Der Request-Body sollte die JSON-Repräsentation eines Benutzer-Objekts enthalten.
     * 
     * @Valid bedeutet, dass das eingehende Benutzer-Objekt automatisch validiert wird, bevor die Methode ausgeführt wird. 
     * Diese Validierung basiert auf Annotationen, die Sie in der Benutzer-Klasse definiert haben (z.B. @NotNull, @Size, @Email etc.).
     * 
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid Benutzer benutzer) throws DuplicateEmailException {
        try {
        	
            // Zusätzliche Validierung oder Geschäftslogik
            if (benutzerService.isEmailAlreadyInUse(benutzer.getEmail())) {
            	LOGGER.infof("E-Mail {} bereits in Verwendung", benutzer.getEmail());
                return Response.status(Response.Status.CONFLICT)
                               .entity("E-Mail-Adresse wird bereits verwendet")
                               .build();
            }


            // Benutzer in der Datenbank speichern
            Benutzer createdBenutzer = benutzerService.createUser(benutzer);
            
            LOGGER.infof("UserController{} createdBenutzer+", createdBenutzer.getId());
            // Erfolgreiche Antwort mit dem erstellten Benutzer (ohne Passwort)
            return Response.status(Response.Status.CREATED)
                           .entity(createdBenutzer)
                           .build();
        } catch (Exception e) {
            e.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Fehler beim Erstellen des Benutzers: " + e.getMessage())
                           .build();
        }
    }
    
    @GET
    @Path("/test")
    public Response testEndpoint() {
        System.out.println("testEndpoint called");
        return Response.ok("Test erfolgreich").build();
    }
    
}

//class ErrorResponse {
//    private String errorCode;
//    private String message;
//
//    public ErrorResponse(String errorCode, String message) {
//        this.errorCode = errorCode;
//        this.message = message;
//    }
//
//	public String getErrorCode() {
//		return errorCode;
//	}
//
//	public void setErrorCode(String errorCode) {
//		this.errorCode = errorCode;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//}