package com.service.gateway.exception;

import javax.naming.AuthenticationException;

public class JwtSecurityException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public JwtSecurityException(String msg) {
        super(msg);
    }
}
