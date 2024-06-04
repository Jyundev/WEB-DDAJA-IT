package com.web.ddajait.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.jwt.JwtFilter;
import com.web.ddajait.config.jwt.TokenProvider;
import com.web.ddajait.model.dto.LoginDto;
import com.web.ddajait.model.dto.TokenDto;
import com.web.ddajait.service.UserService;
import com.web.ddajait.util.CommonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final HttpSession session;
    private final UserService userService ;

    // public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, HttpSession session, UserService userService) {
    //     this.tokenProvider = tokenProvider;
    //     this.authenticationManagerBuilder = authenticationManagerBuilder;
    //     this.session = session;
    //     this.userService = userService;
    // }

    @Operation(summary = "로그인", description = "로그인 API 입니다.")
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(
            @Valid @RequestBody LoginDto loginDto) throws Exception{
        log.info("[AuthController][authorize] Start");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.username(), loginDto.password());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);        

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // 세션 관리 
        Long userId = userService.getUserId(authentication.getName());
        session.setAttribute("userId", userId);

        // 세션 타임아웃을 설정하는 메서드 (1800초 == 30분)
        session.setMaxInactiveInterval(60 * 30);
        
        CommonUtils.checkSessionId(session);
        

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

}
