package com.web.ddajait.config.auth;

import lombok.NoArgsConstructor;

//  요청에 대해서 허용을 할 주소
@NoArgsConstructor
public class AuthenticatedMatchers {
    public static final String[] ignoringArray = {
        "/api-docs",
        "/swagger-ui-custom.html",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/api-docs/**",
        "/swagger-ui.html",
        "/swagger-custom-ui.html",
        "/static/**", 
        "/css/**", 
        "/js/**", 
        "/images/**", 
        "/webjars/**", 
        "/h2-console/**",
        "/index",
        "/static/**"
    };
}
