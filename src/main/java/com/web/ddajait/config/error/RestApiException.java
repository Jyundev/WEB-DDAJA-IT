package com.web.ddajait.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * [Unchecked Exception]
 * RuntimeException 클래스를 상속받는 예외 클래스들은 복구 가능성이 없는 예외
 * 예시 
 * NullPointerException
 * IllegalArgumentException
 * SystemException 
 * 
 * 기존에 있는 에러와 달리 발생한 예외를 처리해줄 예외 클래스를 추가
 */
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ErrorCode errorCode;
}
