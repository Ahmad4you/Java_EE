/**
 * 
 */
package com.home;

import static org.junit.jupiter.api.Assertions.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.home.controller.ZugangsdatenManager;
import com.home.model.Passport;
import com.home.model.User;
import com.home.model.Zugangsdaten;
import com.home.repository.ZugangsdatenService;
import com.home.service.GeneralService;
import com.home.service.impl.ZugangsdatenServiceImpl;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import static org.mockito.Mockito.*;

import java.util.Arrays;

/**
 * 
 * @author Ahmad Alrefai
 */

@ExtendWith(ArquillianExtension.class)
public class ZugangsdatenManagerTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.home.repository")
				.addClasses(ZugangsdatenManager.class, ZugangsdatenService.class, ZugangsdatenServiceImpl.class,
						Zugangsdaten.class, GeneralService.class, Passport.class, User.class)
				.addAsResource("META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
						.resolve("org.hamcrest:hamcrest", "io.rest-assured:rest-assured", "org.mockito:mockito-core")
						.withTransitivity().asFile());
	}

	@Inject
	private ZugangsdatenManager zugangsdatenManager;

	@Mock
	private ZugangsdatenService zugangsdatenService;

	@Mock
	private FacesContext facesContext;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		zugangsdatenManager = new ZugangsdatenManager();
		zugangsdatenManager.setZugangsdatenService(zugangsdatenService);
		zugangsdatenManager.setFacesContext(facesContext);
	}

	@Test
    public void testCreate_Success() {
        // Arrange
        Zugangsdaten zugangsdaten = new Zugangsdaten("test@example.com", "password123");
        zugangsdatenManager.setZugangsdaten(zugangsdaten);
        when(zugangsdatenService.exists(anyString(), anyString())).thenReturn(false);

        // Act
        zugangsdatenManager.create();

        // Assert
        verify(zugangsdatenService).create(any(Zugangsdaten.class));
        verify(facesContext).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    public void testCreate_AlreadyExists() {
        // Arrange
        Zugangsdaten zugangsdaten = new Zugangsdaten("test@example.com", "password123");
        zugangsdatenManager.setZugangsdaten(zugangsdaten);
        when(zugangsdatenService.exists(anyString(), anyString())).thenReturn(true);

        // Act
        zugangsdatenManager.create();

        // Assert
        verify(zugangsdatenService, never()).create(any(Zugangsdaten.class));
        verify(facesContext).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        Zugangsdaten zugangsdaten = new Zugangsdaten("test@example.com", "password123");
        zugangsdaten.setId(1L);
        zugangsdatenManager.setZugangsdaten(zugangsdaten);
        when(zugangsdatenService.exists(anyString(), anyString())).thenReturn(false);
        when(zugangsdatenService.findById(anyLong())).thenReturn(new Zugangsdaten("old@example.com", "oldpassword"));

        // Act
        zugangsdatenManager.update();

        // Assert
        verify(zugangsdatenService).update(eq(1L), any(Zugangsdaten.class));
        verify(facesContext).addMessage(eq(null), any(FacesMessage.class));
    }

    @Test
    public void testDelete() {
        // Act
        zugangsdatenManager.delete(1L);

        // Assert
        verify(zugangsdatenService).delete(1L);
    }

    @Test
    public void testLoad() {
        // Arrange
        when(zugangsdatenService.findAll()).thenReturn(Arrays.asList(new Zugangsdaten(), new Zugangsdaten()));

        // Act
        zugangsdatenManager.load();

        // Assert
        assertNotNull(zugangsdatenManager.getZugangsdatenList());
        assertEquals(2, zugangsdatenManager.getZugangsdatenList().size());
    }
}