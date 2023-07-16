package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.UserModel;
import com.example.arclight.services.UserService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private Logger logger;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Arrange
        List<UserModel> users = new ArrayList<>();
        when(userService.GetUsers()).thenReturn(users);

        // Act
        ResponseEntity<ResponseModel> response = userController.GetUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody().getData());
        verify(userService, times(1)).GetUsers();
    }

    @Test
    void testGetMyProfile_ArclightException() throws ArclightException {
        // Arrange
        String errorMessage = "Invalid user";
        when(userService.GetUser()).thenThrow(new ArclightException(errorMessage));

        // Act
        ResponseEntity<ResponseModel> response = userController.GetMyProfile();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        verify(userService, times(1)).GetUser();
    }

    @Test
    void testGetMyProfile_Exception() throws ArclightException {
        // Arrange
        when(userService.GetUser()).thenThrow(new RuntimeException("Internal server error"));

        // Act
        ResponseEntity<ResponseModel> response = userController.GetMyProfile();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occured", response.getBody().getMessage());
        verify(userService, times(1)).GetUser();
    }

    @Test
    void testGetProfile_ArclightException() throws ArclightException {
        // Arrange
        Long userId = 1L;
        String errorMessage = "Invalid user ID";
        when(userService.GetProfile(userId)).thenThrow(new ArclightException(errorMessage));

        // Act
        ResponseEntity<ResponseModel> response = userController.GetProfile(userId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        verify(userService, times(1)).GetProfile(userId);
    }

    @Test
    void testGetProfile_Exception() throws ArclightException {
        // Arrange
        Long userId = 1L;
        when(userService.GetProfile(userId)).thenThrow(new RuntimeException("Internal server error"));

        // Act
        ResponseEntity<ResponseModel> response = userController.GetProfile(userId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occured", response.getBody().getMessage());
        verify(userService, times(1)).GetProfile(userId);
    }
}
