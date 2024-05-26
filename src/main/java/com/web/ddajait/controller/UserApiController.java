package com.web.ddajait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

// https://hogwart-scholars.tistory.com/entry/Spring-Boot-SpringDoc%EA%B3%BC-Swagger%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-API-%EB%AC%B8%EC%84%9C%ED%99%94-%EC%9E%90%EB%8F%99%ED%99%94%ED%95%98%EA%B8%B0
// @Slf4jhttps://velog.io/@najiexx/Spring-Boot-3%EC%97%90-Swagger-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0springdoc-openapi

//Validated : https://mangkyu.tistory.com/174
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "UUserSER API입니다.")
@Validated
public class UserApiController {

    @Autowired
    UserService userService;

    // 프로필 수정
    @PostMapping("/update")
    @Operation(summary = "프로필 수정", description = "프로필 수정 API 입니다.")
    @Parameter(name = "email", description = "이메일", example = "Jyundev@gmail.com")
    @Parameter(name = "nickname", description = "닉네임", example = "Jyundev")
    @Parameter(name = "password", description = "패스워드", example = "1234qwer")
    @Parameter(name = "age", description = "나이", example = "28")
    @Parameter(name = "gender", description = "성별", example = "male/female")
    @Parameter(name = "job", description = "직업", example = "학생/직장인/취준생")
    @Parameter(name = "interest", description = "관심분야", example = "정보보안/네트워크/운영체제 등")
    @Parameter(name = "profileImage", description = "프로필이미지", example = "URL")
    @Parameter(name = "qualifiedCertificate", description = "취득자격증", example = "['정보처리기사', '리눅스마스터']")
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    public ResponseEntity<ResponseDto<UserDto> > updateUser(@Valid @RequestBody UserDto dto) throws Exception {
        userService.updateUser(dto);
        return ResponseHandler.SUCCESS(dto," 프로필 업데이트 성공");
    }

    
}
// 개인정보 수집 API
// 관심분야, 직업
  


