package com.web.ddajait.config.handler;

import org.springframework.http.ResponseEntity;

import com.web.ddajait.config.constant.ResponseStatus;
import com.web.ddajait.model.dto.Response.ResponseDto;

import lombok.Getter;

@Getter
public class ResponseHandler {

    public static <T> ResponseEntity<ResponseDto<T>> SUCCESS(T data , String msg) {
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;
        ResponseDto<T> response = ResponseDto.of(responseStatus.getHttpStatus(), responseStatus.getCode(), msg + responseStatus.getMessage(), data);

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
