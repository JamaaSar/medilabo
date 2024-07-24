package com.service.risk.exception;

import com.service.risk.exceptions.ErrorHandler;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.webjars.NotFoundException;

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
        BadRequestException exception = new BadRequestException("Bad request");

        // When
        ResponseEntity<Object> responseEntity = errorHandler.handleBadRequestException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }
}
