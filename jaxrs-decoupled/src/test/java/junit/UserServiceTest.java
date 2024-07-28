/**
 * 
 */
package junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.home.model.User;
import com.home.repository.UserRepository;
import com.home.service.UserService;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * 
 * @author Ahmad Alrefai
 */


class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    @Test
    void testGetUserCoupled() {
        User user = new User("Max", "Mustermann", 44);
        when(userRepository.findByFirstNameAndLastName("Max", "Mustermann")).thenReturn(user);

        // Mock ResponseBuilder-Objekt
        ResponseBuilder mockResponseBuilder = mock(ResponseBuilder.class);
        Response mockResponse = mock(Response.class);
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.getEntity()).thenReturn(user);
        when(mockResponseBuilder.build()).thenReturn(mockResponse);

        // Mock die statische Methode Response.ok()
        try (MockedStatic<Response> mockedResponse = Mockito.mockStatic(Response.class)) {
            mockedResponse.when(() -> Response.ok(any())).thenReturn(mockResponseBuilder);

            Response response = userService.getUserCoupled("Max", "Mustermann");

            assertEquals(200, response.getStatus());
            assertEquals(user, response.getEntity());
        }
    }

    @Test
    void testGetUserCoupledNotFound() {
        when(userRepository.findByFirstNameAndLastName("Max", "Mustermann")).thenReturn(null);

        // Mock ResponseBuilder-Objekt
        ResponseBuilder mockResponseBuilder = mock(ResponseBuilder.class);
        Response mockResponse = mock(Response.class);
        when(mockResponse.getStatus()).thenReturn(404);
        when(mockResponseBuilder.build()).thenReturn(mockResponse);

        // Mock die statische Methode Response.status()
        try (MockedStatic<Response> mockedResponse = Mockito.mockStatic(Response.class)) {
            mockedResponse.when(() -> Response.status(Response.Status.NOT_FOUND)).thenReturn(mockResponseBuilder);

            Response response = userService.getUserCoupled("Max", "Mustermann");

            assertEquals(404, response.getStatus());
        }
    }

    @Test
    void testGetUserCoupledException() {
        when(userRepository.findByFirstNameAndLastName("Max", "Mustermann")).thenThrow(new RuntimeException("Database error"));

        // Mock ResponseBuilder-Objekt
        ResponseBuilder mockResponseBuilder = mock(ResponseBuilder.class);
        Response mockResponse = mock(Response.class);
        when(mockResponse.getStatus()).thenReturn(500);
        when(mockResponseBuilder.build()).thenReturn(mockResponse);

        // Mock die statische Methode Response.status()
        try (MockedStatic<Response> mockedResponse = Mockito.mockStatic(Response.class)) {
            mockedResponse.when(() -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)).thenReturn(mockResponseBuilder);

            Response response = userService.getUserCoupled("Max", "Mustermann");

            assertEquals(500, response.getStatus());
        }
    }
}