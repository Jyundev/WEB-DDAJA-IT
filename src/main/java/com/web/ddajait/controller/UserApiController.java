package com.web.ddajait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://hogwart-scholars.tistory.com/entry/Spring-Boot-SpringDoc%EA%B3%BC-Swagger%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-API-%EB%AC%B8%EC%84%9C%ED%99%94-%EC%9E%90%EB%8F%99%ED%99%94%ED%95%98%EA%B8%B0
// @Slf4jhttps://velog.io/@najiexx/Spring-Boot-3%EC%97%90-Swagger-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0springdoc-openapi

//Validated : https://mangkyu.tistory.com/174
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "UserSER API입니다.")
@Validated
public class UserApiController {

    @Autowired
    UserService userService;

    // 프로필 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(
            @Parameter(description = "유저 ID", example = "1") 
            @PathVariable Long id, @Valid @RequestBody UserDto dto) throws Exception {
        userService.updateUser(dto, id);
        return ResponseHandler.SUCCESS(dto, " 프로필 업데이트 성공");
    }

    // 유저,권한 정보를 가져오는 메소드
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "권한조회", description = "권한조회 API 입니다. \"USER\" 또는 \"ADMIN\" 역할을 가진 사용자만 접근할 수 있습니다.")
    public ResponseEntity<ResponseDto<UserDto>> getMyUserInfo() throws Exception {
        log.info("[UserApiController][getMyUserInfo] Start");
        return ResponseHandler.SUCCESS(userService.getMyUserWithAuthorities(), "권한 조회");
    }

}
// 개인정보 수집 API
// 관심분야, 직업
