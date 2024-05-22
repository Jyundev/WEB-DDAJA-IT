package com.web.ddajait.model.dto.response;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    String name();
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();    
    <T> T getData();
} 