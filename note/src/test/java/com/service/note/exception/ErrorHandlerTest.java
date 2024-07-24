package com.service.note.exception;

import com.service.note.exceptions.BadRequestException;
import com.service.note.exceptions.ErrorHandler;
import com.service.note.exceptions.NotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


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
