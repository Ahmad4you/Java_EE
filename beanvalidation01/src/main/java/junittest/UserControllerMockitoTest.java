/**
 * 
 */
package junittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import bcrypt.PasswordHasher;
import controller.UserController;
import entity.Benutzer;
import exception.DuplicateEmailException;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import service.BenutzerService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jboss.logging.Logger;

/**
 * Wenn Integrationstest durchführen soll, bei denen die vollständige Jakarta EE-Umgebung benötigt, kann man Frameworks wie Arquillian 
 * oder TestContainers verwenden. Aber für Unit-Tests ist aktueller Ansatz mit Mockito in einer Java SE-Umgebung genau richtig.
 * 
 * @author Ahmad Alrefai
 */

@ExtendWith(MockitoExtension.class)
class UserControllerMockitoTest {
	private static final Logger LOGGER = Logger.getLogger(UserControllerMockitoTest.class.getName());

	@Mock
	private BenutzerService benutzerService;

	@InjectMocks
	private UserController userController; 
	

	@BeforeEach 
	void setUp() {
		MockitoAnnotations.openMocks(this);
		reset(benutzerService);
		
		LOGGER.info("UserControllerArquillianTest initialized");
        LOGGER.info("BenutzerService in UserControllerArquillianTest injected: " + (benutzerService != null));
//        RuntimeDelegate runtimeDelegate = mock(RuntimeDelegate.class);
//        RuntimeDelegate.setInstance(runtimeDelegate);
//        Response.ResponseBuilder responseBuilder = mock(Response.ResponseBuilder.class);
//        when(runtimeDelegate.createResponseBuilder()).thenReturn(responseBuilder);
//        when(responseBuilder.status(anyInt())).thenReturn(responseBuilder);
//        when(responseBuilder.entity(any())).thenReturn(responseBuilder);
//        when(responseBuilder.build()).thenReturn(mock(Response.class));
	}

//	@Test
//    public void testCreateUser_Success() throws Exception {
//        Benutzer benutzer = new Benutzer();
//        benutzer.setName("Test User");
//        benutzer.setEmail("test@example.com");
//        benutzer.setPassword("password123");
//
//        when(benutzerService.isEmailAlreadyInUse(anyString())).thenReturn(false);
//        when(benutzerService.createUser(any(Benutzer.class))).thenReturn(benutzer);
//
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(benutzer)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("Test User"))
//                .andExpect(jsonPath("$.email").value("test@example.com"));
//
//        verify(benutzerService).isEmailAlreadyInUse("test@example.com");
//        verify(benutzerService).createUser(any(Benutzer.class));
//    }

	@Test
	void testCreateUser_Success() throws DuplicateEmailException {
		// Arrange
		Benutzer benutzer = new Benutzer();
		benutzer.setName("Test User");
		benutzer.setEmail("test@example.com");
		benutzer.setPassword("password123");

		when(benutzerService.isEmailAlreadyInUse(anyString())).thenReturn(false);
		when(benutzerService.createUser(any(Benutzer.class))).thenAnswer(invocation -> {
			Benutzer savedBenutzer = invocation.getArgument(0);
			return savedBenutzer;
		});

		// Act
		Response response = userController.createUser(benutzer);

		// Assert
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

		Benutzer createdBenutzer = (Benutzer) response.getEntity();
		assertNotNull(createdBenutzer);
		assertEquals("Test User", createdBenutzer.getName());
		assertEquals("test@example.com", createdBenutzer.getEmail());

		verify(benutzerService).isEmailAlreadyInUse("test@example.com");
	}

//	@Test
//	void testCreateUser_EmailAlreadyInUse() {
//		// Arrange
//		Benutzer benutzer = new Benutzer();
//		benutzer.setEmail("existing@example.com");
//
//		when(benutzerService.isEmailAlreadyInUse("existing@example.com")).thenReturn(true);
//
//		// Act
//		Response response = userController.createUser(benutzer);
//
//		// Assert
//		assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
//		assertEquals("E-Mail-Adresse wird bereits verwendet", response.getEntity());
//
//		verify(benutzerService).isEmailAlreadyInUse("existing@example.com");
//		verify(benutzerService, never()).createUser(any());
//	}

//	@Test
//	void testCreateUser_Exception() {
//		// Arrange
//		Benutzer benutzer = new Benutzer();
//		benutzer.setEmail("error@example.com");
//
//		when(benutzerService.isEmailAlreadyInUse("error@example.com")).thenReturn(false);
//		when(benutzerService.createUser(benutzer)).thenThrow(new RuntimeException("Database error"));
//
//		// Act
//		Response response = userController.createUser(benutzer);
//
//		// Assert
//		assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
//		assertTrue(((String) response.getEntity()).contains("Fehler beim Erstellen des Benutzers"));
//
//		verify(benutzerService).isEmailAlreadyInUse("error@example.com");
//		verify(benutzerService).createUser(benutzer);
//	}
}