package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.PasswordValidator;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentUser() {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        UserDetails userDetails = mock(UserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        R<UserDetails> expectedResponse = R.createBySuccess(userDetails);

        // Act
        R<UserDetails> result = userController.getCurrentUser();

        // Assert
        assertEquals(expectedResponse, result);
        verify(securityContext, times(1)).getAuthentication();
        verify(authentication, times(1)).getPrincipal();
    }

    @Test
    public void testSave() {
        // Arrange
        User user = new User();
        when(userService.saveUser(user)).thenReturn(1);
        R<Boolean> expectedResponse = R.createBySuccess();

        // Act
        R<Boolean> result = userController.save(user);

        // Assert
        assertEquals(expectedResponse, result);
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testValidPassword() {
        // Arrange
        PasswordValidator passwordValidator = new PasswordValidator();
        when(userService.validPassword(passwordValidator)).thenReturn(true);
        R<Boolean> expectedResponse = R.createBySuccess(true);

        // Act
        R<Boolean> result = userController.validPassword(passwordValidator);

        // Assert
        assertEquals(expectedResponse, result);
        verify(userService, times(1)).validPassword(passwordValidator);
    }

    @Test
    public void testChangePassword() {
        // Arrange
        User user = new User();
        when(userService.updatePasswordById(user)).thenReturn(true);
        R<Boolean> expectedResponse = R.createBySuccess();

        // Act
        R<Boolean> result = userController.changePassword(user);

        // Assert
        assertEquals(expectedResponse, result);
        verify(userService, times(1)).updatePasswordById(user);
    }

    @Test
    public void testValidUsername() {
        // Arrange
        String username = "testUser";
        when(userService.validUsername(username)).thenReturn(true);
        R<Boolean> expectedResponse = R.createBySuccess(true);

        // Act
        R<Boolean> result = userController.validUsername(username);

        // Assert
        assertEquals(expectedResponse, result);
        verify(userService, times(1)).validUsername(username);
    }
}