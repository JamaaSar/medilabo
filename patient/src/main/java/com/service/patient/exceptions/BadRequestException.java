package com.service.patient.exceptions;

public class BadRequestException extends RuntimeException {


    public BadRequestException(String message) {
        super(message);
    }

}
