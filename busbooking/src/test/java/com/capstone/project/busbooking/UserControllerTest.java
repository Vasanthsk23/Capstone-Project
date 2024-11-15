package com.capstone.project.busbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.security.Principal;

@SpringBootTest
public class UserControllerTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void testshowRegistrationForm(){
        Model model = Mockito.mock(Model.class);
        String result = userController.showRegistrationForm(model);
        Assertions.assertEquals("register", result);
    }

    @Test
    public void testlogin(){
        String result = userController.login();
        Assertions.assertEquals("login", result);
    }

    @Test
    public void testshowUserProfileO(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        String result = userController.showUserProfileO(model, principal);
        Assertions.assertEquals("user-profile", result);
    }

    @Test
    public void testchangePassword(){
        String result = userController.changePassword();
        Assertions.assertEquals("change-password", result);
    }

}
