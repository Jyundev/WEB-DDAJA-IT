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
import com.web.ddajait.model.dto.CertificateInfo.EligibilityDto;
import com.web.ddajait.service.CertificateInfoService;
import com.web.ddajait.util.JsonToObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://ttl-blog.tistory.com/756

@RestController
@Tag(name = "Test", description = "Test API 입니다.")
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {


    private final CertificateInfoService certificateInfoService;
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

    @GetMapping("/all")
    @Operation(summary = "모든 자격증 데이터", description = "모든 자격증 데이터를 가져오는 API 입니다.")
    public EligibilityDto getAllCertificate() {
        log.info("[TestController][getAllCertificate] Starts");
        List<CertificateInfoDto> certificateList = certificateInfoService.getAllCertificate();
        
        EligibilityDto result = new EligibilityDto();

        if (!certificateList.isEmpty()) {
            CertificateInfoDto dto = certificateList.get(0);

            result = (EligibilityDto) JsonToObject.transObject(dto.getEligibility(), result);
            
            log.info("[TestController][getAllCertificate] JSON : {}", result);
        } else {
            log.warn("[TestController][getAllCertificate] No certificates found");
        }

        return result;
    }


}

