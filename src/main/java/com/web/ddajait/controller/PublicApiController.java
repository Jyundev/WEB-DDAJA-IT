package com.web.ddajait.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "Public", description = "Public API 입니다.")
@RequestMapping("/api/v1/public")
public class PublicApiController {
    private static final Logger log = LoggerFactory.getLogger(PublicApiController.class);

    @Autowired
    UserService userService;
    
    @Operation(summary = "회원가입", description = "회원가입 API 입니다. email, nickname, password 는 필수이며, age, gender는 선택사항입니다.")
    @Parameter(name = "email", description = "유저 이메일 값", example = "Jyundev@gmail.com", required = true)
    @Parameter(name = "nickname", description = "닉네임 값", example = "Jyundev", required = true)
    @Parameter(name = "password", description = "유저 비밀번호", example = "1234qwer", required = true)
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<UserDto>> join(@Valid @RequestBody UserDto dto) throws Exception {
        log.info("[PublicController][join] Start - Email: {}, Nickname: {}", dto.getEmail(), dto.getNickname());
        userService.createMember(dto);
        log.info("[PublicController][join] Success - User: {}", dto.getNickname());

        return ResponseHandler.SUCCESS(dto, "회원가입");

    }

    // 유저,권한 정보를 가져오는 메소드
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "권한조회", description = "권한조회 API 입니다. \"USER\" 또는 \"ADMIN\" 역할을 가진 사용자만 접근할 수 있습니다.")
    public ResponseEntity<ResponseDto<UserDto>> getMyUserInfo() throws Exception {
        return ResponseHandler.SUCCESS(userService.getMyUserWithAuthorities(), "권한 조회");
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "특정유저 권한조회", description = "권한조회 API 입니다.  \"ADMIN\" 역할을 가진 사용자만 접근할 수 있습니다")
    @Parameter(name = "email", description = "유저 이메일(ID)", example = "Jyundev@gmail.com", required = true)
    public ResponseEntity<ResponseDto<UserDto>> getUserInfo(@PathVariable("email") String email) throws Exception {
        return ResponseHandler.SUCCESS(userService.getUserWithAuthorities(email), email+" 권한 조회");

    }

}
