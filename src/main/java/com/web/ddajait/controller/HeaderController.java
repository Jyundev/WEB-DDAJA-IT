package com.web.ddajait.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.Response.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Header", description = "Header API")
public class HeaderController {

    @Operation(summary = "헤더 값 확인 ", description = "헤더 값 확인 API 입니다. 모든 요청들에 대해 컨트롤러 내부에서 Authorization 헤더 값을 그대로 추출합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<Map<String, String>>> authHeaderChecker(HttpServletRequest request){
        Map<String, String> response = new HashMap<>(){{
            put("Authorization",  request.getHeader("Authorization"));
        }};

        return ResponseHandler.SUCCESS(response, "Header JWT Check");
    }
    
}
