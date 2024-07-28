/**
 * 
 */
package junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.home.model.User;
import com.home.repository.UserRepository;
import com.home.service.UserService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.core.Response;

class UserServiceTest2 {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    
    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        userService.setValidator(validator);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);

        Response response = userService.createUser(user);
        assertEquals(201, response.getStatus());
    }

    @Test
    void testCreateUserValidationFailure() {
        User user = new User();
        user.setFirstName("");
        user.setLastName("Doe");
        user.setAge(30);

        Response response = userService.createUser(user);
        assertEquals(400, response.getStatus());
    }
}