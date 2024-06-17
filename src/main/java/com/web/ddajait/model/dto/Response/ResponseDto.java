package com.web.ddajait.model.dto.Response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private HttpStatus httpStatus;
    private int code;
    private String message;
    private T data;

    public static <T> ResponseDto<T> of(HttpStatus httpStatus, int code, String message, T data) {
        return ResponseDto.<T>builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}