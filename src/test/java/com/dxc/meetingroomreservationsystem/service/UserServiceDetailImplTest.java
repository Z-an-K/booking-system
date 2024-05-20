package com.dxc.meetingroomreservationsystem.service;

import com.dxc.meetingroomreservationsystem.mapper.UserMapper;
import com.dxc.meetingroomreservationsystem.pojo.MyUser;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.pojo.enums.UserRoleEnums;
import com.dxc.meetingroomreservationsystem.service.impl.UserServiceDetailImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceDetailImplTest {

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceDetailImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setRole(UserRoleEnums.ADMIN.toString());
        when(mapper.getUserByUserName(username)).thenReturn(user);

        // Act
        UserDetails result = userService.loadUserByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertTrue(result.getAuthorities().contains(new SimpleGrantedAuthority(UserRoleEnums.ADMIN.toString())));
        verify(mapper, times(1)).getUserByUserName(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "testUser";
        when(mapper.getUserByUserName(username)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
        verify(mapper, times(1)).getUserByUserName(username);
    }
}