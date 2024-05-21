package com.web.ddajait.config.error;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// https://studyandwrite.tistory.com/502
// https://velog.io/@leehyeonmin34/exception-handling

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final boolean success = false;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<ValidationError> errors;

    public static ErrorResponse of(HttpStatus httpStatus,int code, String message){
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
            return bindingResult.getFieldErrors().stream()
                    .map(ValidationError :: new)
                    .toList();
        }

    }
}
