package com.web.ddajait.config.error.custom;

public class MemoNotFoundException extends RuntimeException {
    public MemoNotFoundException(String message) {
        super(message);
    }
}