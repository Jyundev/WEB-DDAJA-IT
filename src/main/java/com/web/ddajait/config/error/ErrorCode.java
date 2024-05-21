package com.web.ddajait.config.error;

import org.springframework.http.HttpStatus;

/*
 * 에러 코드를 정의
 * 에러 이름, HttpStatus code, 에러 메세지 포함 
 */
public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();
}