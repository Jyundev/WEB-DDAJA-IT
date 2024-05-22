package com.web.ddajait.config.constant.Code;

import org.springframework.http.HttpStatus;

import com.web.ddajait.config.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * 공통 에러 코드
 * httpStatus와 message를 필드로 가지고 @Getter 어노테이션을 사용하여 
 * implements한 메소드들을 굳이 코드로 재정의하지 않음  
 */

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorCode{
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST,400, "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "필수 파라미터가 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    DATA_ACEESS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "데이터에 접근 할 수 없습니다"),
    OK(HttpStatus.OK, 200, "Ok")
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
    
}
