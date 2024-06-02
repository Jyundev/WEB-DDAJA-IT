package com.web.ddajait.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.service.CertificateInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("api/v1/certificate")
@Tag(name = "Certoficate", description = "Certoficate API")
@Slf4j
@AllArgsConstructor
public class CertificateController {

    private final CertificateInfoService certificateInfoService;

    @GetMapping("/all")
    @Operation(summary = "모든 자격증 데이터", description = "모든 자격증 데이터를 가져오는 API 입니다.")
    public List<CertificateInfoDto> getAllCertificate(){
        log.info("[CertificateController][getAllCertificate] Starts");
        
        return certificateInfoService.getAllCertificate();

    }
}
