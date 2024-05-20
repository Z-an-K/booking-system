package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dxc.meetingroomreservationsystem.mapper.UserMapper;
import com.dxc.meetingroomreservationsystem.pojo.PasswordValidator;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByUserName() {
        // Arrange
        String userName = "testUser";
        User user = new User();
        when(userMapper.getUserByUserName(userName)).thenReturn(user);

        // Act
        User result = userService.getUserByUserName(userName);

        // Assert
        assertEquals(user, result);
        verify(userMapper, times(1)).getUserByUserName(userName);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User();
        user.setPassword("password");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userMapper.insert(user)).thenReturn(1);

        // Act
        int result = userService.saveUser(user);

        // Assert
        assertEquals(1, result);
        assertEquals("encodedPassword", user.getPassword());
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userMapper, times(1)).insert(user);
    }

    @Test
    public void testValidPassword() {
        // Arrange
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.setOldPassword("123456");
        passwordValidator.setHashPassword("$2a$10$4Lo1Zb7Y9GWyigldh8DD4eg1cFVyWkp4bW01VypKxla9ht9jVy2Tu");
        // Act
        boolean result = userService.validPassword(passwordValidator);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testUpdatePasswordById() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setPassword("newPassword");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        OngoingStubbing<Boolean> booleanOngoingStubbing = when(userService.update(any(User.class), any(Wrapper.class))).thenReturn(true);

        // Act
        boolean result = userService.updatePasswordById(user);

        // Assert
        assertTrue(result);
        assertEquals("encodedPassword", user.getPassword());
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userService, times(1)).update(any(Wrapper.class));
    }

    @Test
    public void testValidUsername() {
        // Arrange
        String username = "testUser";
        when(userService.getOne(any(LambdaQueryChainWrapper.class),eq(true))).thenReturn(null);

        // Act
        boolean result = userService.validUsername(username);

        // Assert
        assertTrue(result);
        verify(userMapper, times(1)).selectOne(any(LambdaUpdateWrapper.class),eq(true));
    }
}