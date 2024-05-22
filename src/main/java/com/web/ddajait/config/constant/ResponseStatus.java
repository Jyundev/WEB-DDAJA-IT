package com.web.ddajait.config.constant;

import org.springframework.http.HttpStatus;

import com.web.ddajait.model.dto.response.ResponseCode;

public enum ResponseStatus implements ResponseCode{

    SUCCESS(200, HttpStatus.OK, "성공했습니다"),
    FAILURE(600, HttpStatus.BAD_REQUEST, "실패했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ResponseStatus(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public <T> T getData() {
        // 기본적으로 null 반환
        return null;
    }
}
