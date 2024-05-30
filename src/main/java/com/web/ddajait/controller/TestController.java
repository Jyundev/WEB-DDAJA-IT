package com.web.ddajait.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;

import io.swagger.v3.oas.annotations.tags.Tag;

// https://ttl-blog.tistory.com/756

@RestController
@Tag(name = "Test", description = "Test API 입니다.")
@RequestMapping("/api/v1/test")
public class TestController {


    private List<CertificateInfoDto> certificateList = new ArrayList<>();

    @PostMapping("/post/certificate")
    public ResponseEntity<ResponseDto<List<CertificateInfoDto>>> createCertificate(@RequestBody CertificateInfoDto certificate) {
        certificateList.add(certificate);
        return ResponseHandler.SUCCESS(certificateList,"Certificate created successfully");
    }

    @GetMapping("get/certificate")
    public ResponseEntity<List<CertificateInfoDto>> getCertificates() {
        return ResponseEntity.ok(certificateList);
    }
}

