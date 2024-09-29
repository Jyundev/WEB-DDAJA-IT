package com.web.ddajait.config.auth;

import org.springframework.http.HttpStatus;

import com.web.ddajait.config.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthenticationTypes implements ErrorCode {

    BadCredentialsException(HttpStatus.BAD_REQUEST, 401, "비밀번호가 불일치합니다."),
    UsernameNotFoundException(HttpStatus.BAD_REQUEST, 402, "계정이 존재하지 않습니다."),
    AccountExpiredException(HttpStatus.BAD_REQUEST, 403, "만료된 계정입니다."),
    CredentialsExpiredException(HttpStatus.BAD_REQUEST, 404, "비밀번호가 만료됐습니다."),
    DisabledException(HttpStatus.BAD_REQUEST, 405, "계정이 비활성화 상태입니다."),
    LockedException(HttpStatus.BAD_REQUEST, 406, "계정이 잠금상태입니다."),
    NoneException(HttpStatus.BAD_REQUEST, 407, "알 수 없는 에러 입니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

}
