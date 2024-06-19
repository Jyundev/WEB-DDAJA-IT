package com.web.ddajait.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String message) {
        super(message);
    }

}
