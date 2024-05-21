package com.web.ddajait.config.constant;

import org.springframework.http.HttpStatus;

import com.web.ddajait.config.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityErrorCode implements ErrorCode{

    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"존재하지 않는 사용자입니다"),
    MEMBER_ID_ALREADY_EXISTS_ERROR(HttpStatus.CONFLICT,409, "는 이미 존재하는 ID입니다"),
    MEMBER_EMAIL_ALREADY_EXISTS_ERROR(HttpStatus.CONFLICT,409,"는 이미 존재하는 이메일입니다."),
    INACTIVE_USER_ERROR(HttpStatus.FORBIDDEN, 403,"권한이 없는 사용자입니다"),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
