package com.web.ddajait.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.CertificationRegistrationDto;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.service.CertificateInfoService;
import com.web.ddajait.service.CertificationRegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/certificate")
@Tag(name = "Certificate", description = "Certoficate API")
@Slf4j
@AllArgsConstructor
public class CertificateController {

    private final CertificateInfoService certificateInfoService;
    private final CertificationRegistrationService certificationRegistrationService;

    @GetMapping("/all")
    @Operation(summary = "모든 자격증 데이터", description = "모든 자격증 데이터를 가져오는 API 입니다.")
    public List<CertificateInfoDto> getAllCertificate() {
        log.info("[CertificateController][getAllCertificate] Starts");

        return certificateInfoService.getAllCertificate();

    }

    @GetMapping("/register/all")
    @Operation(summary = "모든 자격증 접수 일정 데이터", description = "모든 자격증의 접수 일정 데이터를 가져오는 API 입니다.")
    public List<CertificationRegistrationDto> getAllCertificateRegistration() {
        log.info("[CertificateController][getAllCertificateRegistration] Starts");

        return certificationRegistrationService.getAllCerticationResgitration();

    }
}
