package com.web.ddajait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Admin API")
@RequestMapping("/api/v1/admin")
public class AdminApiController {
    
    @Autowired
    private UserService userService;

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @Tag(name = "Admin API")
    @Operation(summary = "유저탈퇴", description = "유저탈퇴 API 입니다. 유저 데이터가 삭제됩니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        userService.deleteUser(userId);        
        return ResponseHandler.SUCCESS( null, "유저탈퇴에 성공했습니다");
    }



}
