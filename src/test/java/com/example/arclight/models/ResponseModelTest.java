package com.example.arclight.models;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseModelTest {

    @Test
    void testOkWithMessage() {
        // Arrange
        String message = "Success";

        // Act
        ResponseEntity<ResponseModel> responseEntity = ResponseModel.Ok(message);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ResponseModel responseModel = responseEntity.getBody();
        assertEquals(HttpStatus.OK.value(), responseModel.getStatus());
        assertEquals(message, responseModel.getMessage());
        assertNull(responseModel.getData());
    }

    @Test
    void testOkWithMessageAndData() {
        // Arrange
        String message = "Success";
        Object data = new Object();

        // Act
        ResponseEntity<ResponseModel> responseEntity = ResponseModel.Ok(message, data);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ResponseModel responseModel = responseEntity.getBody();
        assertEquals(HttpStatus.OK.value(), responseModel.getStatus());
        assertEquals(message, responseModel.getMessage());
        assertEquals(data, responseModel.getData());
    }

    @Test
    void testFailWithMessageAndStatus() {
        // Arrange
        String message = "Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Act
        ResponseEntity<ResponseModel> responseEntity = ResponseModel.Fail(message, status);

        // Assert
        assertEquals(status, responseEntity.getStatusCode());
        ResponseModel responseModel = responseEntity.getBody();
        assertEquals(status.value(), responseModel.getStatus());
        assertEquals(message, responseModel.getMessage());
        assertNull(responseModel.getData());
    }

    @Test
    void testFailWithOkStatus() {
        // Arrange
        String message = "Error";
        HttpStatus status = HttpStatus.OK;

        // Act
        ResponseEntity<ResponseModel> responseEntity = ResponseModel.Fail(message, status);

        // Assert
        assertNull(responseEntity);
    }
}