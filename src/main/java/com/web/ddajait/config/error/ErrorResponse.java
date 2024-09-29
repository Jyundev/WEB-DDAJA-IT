package com.web.ddajait.config.error;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// https://seungh1024.tistory.com/55 
/*
 * {
 *    "success": false,
 *    "httpStatus": "CONFLICT",
 *    "code": 409,
 *    "message": "TEST는 이미 존재하는 회원입니다"
 * }
 */

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final boolean success = false;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    //  erros가 없다면 응답으로 내려가지 않도록 JsonInclude 어노테이션 추가 
    //  NON_NULL : null인 데이터는 제외한다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<ValidationError> errors;

    public static ErrorResponse of(HttpStatus httpStatus, int code, String message){
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
    public static ErrorResponse of(HttpStatus httpStatus,int code, String message, BindingResult bindingResult){
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .errors(ValidationError.of(bindingResult))
                .build();
    }
/*
 *  @Valid를 사용했을 때 에러가 발생한 경우 어느 필드에서 에러가 발생했는지 응답 하기 위한 ValidationError
 */
    @Getter
    public static class ValidationError{
        private final String field;
        private final String value;
        private final String message;

        private ValidationError(FieldError fieldError){
            this.field = fieldError.getField();
            this.value = fieldError.getRejectedValue() == null? "" :fieldError.getRejectedValue().toString() ;
            this.message = fieldError.getDefaultMessage();
        }

        public static List<ValidationError> of(final BindingResult bindingResult){
            return bindingResult.getFieldErrors().stream() // FieldError로 이루어진 List를 반환
                    .map(ValidationError :: new) // 해당 에러들을 전부  ValidationError를 이용해ㅐ 객체 생성 후 List형태로 반환
                    .toList();
        }

    }
}
