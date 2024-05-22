package com.web.ddajait.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "Public API", description = "Public API 입니다.")
@RequestMapping("/api/v1/public")
public class PublicApiController {
    private static final Logger log = LoggerFactory.getLogger(PublicApiController.class);

    @Autowired 
    UserService userService;

    @Tag(name = "Public API")
    @Operation(summary = "회원가입", description = "회원가입 API 입니다. email, nickname, password 는 필수이며, age, gender는 선택사항입니다.")
    @PostMapping("/join")
    public void join(@Valid @RequestBody UserDto dto, HttpServletResponse response) throws Exception {
        log.info("[PublicController][join] Start - Email: {}, Nickname: {}", dto.getEmail(), dto.getNickname());

        userService.createMemberr(dto);
        log.info("[PublicController][join] Success - User: {}", dto.getNickname());
        response.sendRedirect("/loginPage"); // 리다이렉션 URL 설정

    }
}
