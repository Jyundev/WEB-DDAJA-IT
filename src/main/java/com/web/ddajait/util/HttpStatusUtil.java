package com.web.ddajait.util;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class HttpStatusUtil {

    /**
     * 주어진 문자열이 유효한 HttpStatus 값인지 검사합니다.
     * 
     * @param status 검사할 문자열
     * @return 유효한 HttpStatus 값인지 여부
     */
    public static boolean isValidHttpStatus(Integer status) {
        return Arrays.stream(HttpStatus.values())
                    .anyMatch(httpStatus -> httpStatus.name().equals(status));
    }

    /**
     * 주어진 문자열을 HttpStatus로 변환합니다.
     * 
     * @param status 변환할 문자열
     * @return 변환된 HttpStatus
     * @throws IllegalArgumentException 유효하지 않은 HttpStatus 값일 경우
     */
    public static HttpStatus getHttpStatus(Integer status) throws IllegalArgumentException {
        if (isValidHttpStatus(status)) {
            return HttpStatus.valueOf(status);
        } else {
            throw new IllegalArgumentException();
        }
    }
}