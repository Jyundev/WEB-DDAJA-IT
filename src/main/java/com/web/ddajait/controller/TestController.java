package com.web.ddajait.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.web.ddajait.model.dto.CertificateInfoDto;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// https://ttl-blog.tistory.com/756

@RestController
@Tag(name = "Test", description = "Test API 입니다.")
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/json/string")
    @Operation(summary = "Json 데이터 반환", description = "JSON 데이터 테스트")
    public ResponseEntity<ResponseDto<Object>> serializationApi() {
        String result = "";
        CertificateInfoDto personDto = CertificateInfoDto.builder()
                .certificateName("리눅스마스터 1급")
                .certificateFullName("리눅스마스터 1급")
                .difficulty("상")
                .eligibility()
                .build();
    }
}






