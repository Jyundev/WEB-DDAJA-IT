package com.web.ddajait.config.error;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;
/*
 * https://seungh1024.tistory.com/55
 * 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해준다
 * 모든 컨트롤러에 대한 예외처리를 하나의 클래스로 할 수 있다.
 * 일관성있는 에러 응답을 보내줄 수 있다.
 * if,else 또는 try,catch를 사용하지 않아 코드의 가독성이 좋아지고 수정하기 용이하다.
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
   
 
}
