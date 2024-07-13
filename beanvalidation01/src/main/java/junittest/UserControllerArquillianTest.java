/**
 * 
 */
package junittest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jaxrs.JakartaRestConfiguration;
import service.BenutzerService;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jboss.arquillian.junit5.ArquillianExtension;

import bcrypt.PasswordHasher;

import static org.junit.jupiter.api.Assertions.*;
import controller.UserController;
import entity.Benutzer;
import exception.DuplicateEmailException;

/**
 * 
 * @author Ahmad Alrefai
 */

//@RunWith(Arquillian.class) old
@ExtendWith(ArquillianExtension.class)
public class UserControllerArquillianTest {

	private static final String RESOURCE_PREFIX = "/api/benutzer";

	@Deployment
	public static WebArchive createDeployment() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
	            .addClasses(UserController.class, BenutzerService.class, Benutzer.class, JakartaRestConfiguration.class, DuplicateEmailException.class)
	            .addAsResource("META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	            .addAsLibraries(Maven.resolver()
	                    .loadPomFromFile("pom.xml")
	                    .resolve("org.hamcrest:hamcrest", "io.rest-assured:rest-assured", "org.mindrot:jbcrypt") // hier add weitere Abhängigkeitn 
	                    .withTransitivity()
	                    .asFile());
	}

	@ArquillianResource
	private URL deploymentUrl;

	@Test
	public void testCreateUser() {
	    Benutzer newUser = new Benutzer();
	    newUser.setName("testuser" + System.currentTimeMillis());  
	    newUser.setEmail("testuser" + System.currentTimeMillis() + "@outlook.com");  
	    newUser.setPassword("testpasswort1239");

	    Client client = ClientBuilder.newClient();
	    WebTarget target = client.target(deploymentUrl.toString()).path(RESOURCE_PREFIX);

	    try {
	        Response response = target.request(MediaType.APPLICATION_JSON)
	                .post(Entity.json(newUser));

	        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

	        Benutzer createdUser = response.readEntity(Benutzer.class);
	        

	        assertNotNull(createdUser.getId());
	        assertEquals(newUser.getName(), createdUser.getName());
	        assertEquals(newUser.getEmail(), createdUser.getEmail());
	        
	        // Überprüfen Sie hier nicht das Passwort, da es gehasht sein sollte
	        
	    } finally {
	        client.close();
	    }
	}
	
	private Response sendRequest(String path, String method) {
	    Client client = ClientBuilder.newClient();
	    WebTarget target = client.target(deploymentUrl.toString()).path(path);
	    
	    if ("GET".equalsIgnoreCase(method)) {
	        return target.request().get();
	    } else if ("POST".equalsIgnoreCase(method)) {
	        return target.request().post(Entity.json(""));
	    }
	    // Füge hier weitere Methoden hinzu, wenn nötig
	    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
	}

//	@Test
//	public void testGetEndpoint() {
//	    try (Response response = sendRequest("/api/benutzer/test", "GET")) {
//	        assertEquals(200, response.getStatus());
//	        String responseBody = response.readEntity(String.class);
//	        assertEquals("Test erfolgreich", responseBody);
//	    }
//	}

}