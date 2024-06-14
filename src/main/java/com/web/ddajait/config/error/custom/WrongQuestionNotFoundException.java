package com.web.ddajait.config.error.custom;

public class WrongQuestionNotFoundException extends RuntimeException {
    public WrongQuestionNotFoundException(String message) {
        super(message);
    }
}
