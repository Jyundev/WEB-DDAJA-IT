package com.web.ddajait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API")
@RequestMapping("/api/v1/admin")
public class AdminApiController {
    
    @Autowired
    private UserService userService;

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @Operation(summary = "유저탈퇴", description = "유저탈퇴 API 입니다. 유저 데이터가 삭제됩니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        userService.deleteUser(userId);        
        return ResponseHandler.SUCCESS( null, "유저탈퇴에 성공했습니다");
    }

    //  조회
    @Operation(summary = "유저조회", description = "유저조회 API 입니다. 이메일(ID) 기준 유저를 조회합니다.")
    @Parameter(name = "userEmail", description = "로그인 유저 이메일 값", example = "test@gmail.com", required = true)
    @GetMapping("/find/email/{userEmail}")
    public ResponseEntity<ResponseDto<UserDto>> findByUserEmail(@PathVariable("userEmail") String userEmail) throws Exception {
        UserDto userDto = userService.findByEmail(userEmail);
        return ResponseHandler.SUCCESS(userDto, "유저조회에 성공했습니다.");
    }
       



}
