package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.services.TranslationService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TranslationControllerTest {
    @Mock
    private TranslationService translationService;

    @InjectMocks
    private TranslationController translationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTranslation_ArclightException() throws ArclightException {
        // Arrange
        Long translationId = 1L;
        String errorMessage = "Invalid translation ID";
        when(translationService.GetTranslation(translationId)).thenThrow(new ArclightException(errorMessage));

        // Act
        ResponseEntity<ResponseModel> response = translationController.GetTranslation(translationId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        verify(translationService, times(1)).GetTranslation(translationId);
    }

    @Test
    void testGetTranslation_Exception() throws ArclightException {
        // Arrange
        Long translationId = 1L;
        when(translationService.GetTranslation(translationId)).thenThrow(new RuntimeException("Internal server error"));

        // Act
        ResponseEntity<ResponseModel> response = translationController.GetTranslation(translationId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occured", response.getBody().getMessage());
        verify(translationService, times(1)).GetTranslation(translationId);
    }

    @Test
    void testDelete_Success() throws ArclightException {
        // Arrange
        Long translationId = 1L;

        // Act
        ResponseEntity<ResponseModel> response = translationController.Delete(translationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(translationId, response.getBody().getData());
        verify(translationService, times(1)).Delete(translationId);
    }
}