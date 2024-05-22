package com.web.ddajait.config.constant;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    String name();
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();    
    <T> T getData();
} 