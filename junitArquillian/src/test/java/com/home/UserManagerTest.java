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

import com.home.controller.UserManager;
import com.home.model.Passport;
import com.home.model.User;
import com.home.model.Zugangsdaten;
import com.home.repository.UserService;
import com.home.service.GeneralService;
import com.home.service.impl.UserServiceImpl;

import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 
 * @author Ahmad Alrefai
 */

@ExtendWith(ArquillianExtension.class)
public class UserManagerTest {
	
	@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.home.repository")
                .addClasses(UserManager.class, UserService.class, UserServiceImpl.class, 
                            User.class, Zugangsdaten.class, GeneralService.class, Passport.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(Maven.resolver()
                        .loadPomFromFile("pom.xml")
                        .resolve("org.hamcrest:hamcrest", "io.rest-assured:rest-assured", "org.mockito:mockito-core")
                        .withTransitivity()
                        .asFile());
    }

    @Inject
    private UserManager userManager;

    @Mock
    private UserService userService;

    @Mock
    private FacesContext facesContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userManager.setUserService(userService);
        userManager.setFacesContext(facesContext);
    }

    @Test
    public void testInit() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userService.findAll()).thenReturn(mockUsers);

        userManager.init();

        assertNotNull(userManager.getUser());
        assertNotNull(userManager.getUser().getZugangsdaten());
        assertEquals(mockUsers, userManager.getUsers());
    }

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);
        Zugangsdaten zugangsdaten = new Zugangsdaten();
        zugangsdaten.setEmail("john@example.com");
        user.setZugangsdaten(zugangsdaten);

        userManager.setUser(user);

        when(userService.userExists("John", "Doe")).thenReturn(false);

        userManager.createUser();

        verify(userService).create(any(User.class));
        verify(facesContext).addMessage(isNull(), any(FacesMessage.class));
    }

    @Test
    public void testCreateUser_UserExists() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        userManager.setUser(user);

        when(userService.userExists("John", "Doe")).thenReturn(true);

        userManager.createUser();

        verify(userService, never()).create(any(User.class));
        // Verify that error message was added
    }

    @Test
    public void testUpdateUser_Success() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");

        userManager.setUser(updatedUser);

        when(userService.findById(userId)).thenReturn(existingUser);
        when(userService.update(eq(userId), any(User.class))).thenReturn(updatedUser);
        when(userService.userExists("Jane", "Doe")).thenReturn(false);

        // Act
        userManager.updateUser();

        // Assert
        verify(userService).update(eq(userId), any(User.class));
        assertEquals("Jane", userManager.getUser().getFirstName());
        assertEquals("Doe", userManager.getUser().getLastName());
        verify(facesContext).addMessage(isNull(), any(FacesMessage.class));
    }
    
    @Test
    public void testDeleteUser() {
        userManager.deleteUser(1L);

        verify(userService).delete(1L);
        // You might want to verify that loadUsers was called
    }
}