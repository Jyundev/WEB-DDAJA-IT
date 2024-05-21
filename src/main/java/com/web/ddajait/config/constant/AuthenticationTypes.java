package com.web.ddajait.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationTypes {

    BadCredentialsException(401, "비밀번호가 불일치합니다."),
    UsernameNotFoundException(402, "계정이 존재하지 않습니다."),
    AccountExpiredException(403, "만료된 계정입니다."),
    CredentialsExpiredException(404, "비밀번호가 만료됐습니다."),
    DisabledException(405, "계정이 비활성화 상태입니다."),
    LockedException(406, "계정이 잠금상태입니다."),
    NoneException(407, "알 수 없는 에러 입니다.");

    private int code;
    private String msg;

}
