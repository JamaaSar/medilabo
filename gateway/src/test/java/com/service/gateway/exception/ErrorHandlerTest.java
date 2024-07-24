package com.service.gateway.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class ErrorHandlerTest {
    private final ErrorHandler errorHandler = new ErrorHandler();

    @Test
    public void handleNotFoundException_shouldReturnNotFoundStatus() {
        // Given
        NotFoundException exception = new NotFoundException("Not found");

        // When
        ResponseEntity<String> responseEntity = errorHandler.handleNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void handleBadRequestException_shouldReturnBadRequestStatus() {
        // Given
        JwtSecurityException exception = new JwtSecurityException("Bad request");

        // When
        ResponseEntity<String> responseEntity =
                errorHandler.jwtSecurityException(exception);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }
}
