package com.web.ddajait.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.web.ddajait.model.dto.Response.ResponseDto;

import lombok.extern.slf4j.Slf4j;


/**
 * 로깅 관련 AOP 구성
 * 
 * 이 클래스는 Spring AOP를 사용하여 다양한 로깅 관련 작업을 수행합니다.
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * Controller의 모든 메소드 실행 전에 로그를 출력합니다.
     */
    @Before("execution(* com.web.ddajait.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("메소드 실행 전: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Controller의 메소드가 정상적으로 반환된 후에 반환값을 로그에 출력합니다.
     */
    @AfterReturning(pointcut = "execution(* com.web.ddajait.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("메소드 정상 실행 후 - 반환값: {}", extractResponseDto(result));
    }

    /**
     * Controller의 메소드에서 예외가 발생한 경우 예외 정보를 로그에 출력합니다.
     */
    @AfterThrowing(pointcut = "execution(* com.web.ddajait.controller.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("메소드 실행 중 예외 발생 - 메소드: {}, 예외: {}", joinPoint.getSignature().toShortString(), error.getMessage());
    }

    /**
     * Controller의 메소드 실행 후에 항상 로그를 출력합니다.
     */
    @After("execution(* com.web.ddajait.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("메소드 실행 후: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Around 어드바이스를 사용하여 Controller의 메소드 실행 전후에 로그를 출력합니다.
     */
    @Around("execution(* com.web.ddajait.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("메소드 실행 전: {}", joinPoint.getSignature().toShortString());
        try {
            Object result = joinPoint.proceed(); // 대상 메소드 실행
            log.info("메소드 정상 실행 후 - 반환값: {}", extractResponseDto(result));
            return result;
        } catch (Throwable throwable) {
            log.error("메소드 실행 중 예외 발생 - 예외: {}", throwable.getMessage());
            throw throwable;
        } finally {
            log.info("메소드 실행 후: {}", joinPoint.getSignature().toShortString());
        }
    }

    /**
     * 반환값이 ResponseEntity인 경우 그 내부의 ResponseDto의 데이터를 추출하여 반환합니다.
     * @param result AOP Around 어드바이스에서 반환된 결과 객체
     * @return 반환값이 ResponseDto인 경우 그 안의 데이터, 아닌 경우 null
     */
    private Object extractResponseDto(Object result) {
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            Object body = responseEntity.getBody();
            if (body instanceof ResponseDto) {
                return ((ResponseDto<?>) body).getData();
            }
        }
        return null;
    }
}