package com.web.ddajait.model.dto.response;

import org.springframework.http.ResponseEntity;

import com.web.ddajait.config.constant.Code.ResponseStatus;

import lombok.Getter;

@Getter
public class ResponseHandler {

    public static <T> ResponseEntity<ResponseDto<T>> SUCCESS_SAMPLE(T data) {
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;
        ResponseDto<T> response = ResponseDto.of(responseStatus.getHttpStatus(), responseStatus.getCode(), responseStatus.getMessage(), data);

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
