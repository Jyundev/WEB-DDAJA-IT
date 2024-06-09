package com.web.ddajait.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.ddajait.model.dto.CertificationRegistrationDto;
import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.model.dto.Calendar.CalendarDto;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityJsonWrapper;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;
import com.web.ddajait.model.dto.CertificateInfo.ExamStandard.ExamStandardJsonWrapper;
import com.web.ddajait.model.entity.PartQuestionEntity;
import com.web.ddajait.service.CertificateInfoService;
import com.web.ddajait.service.CertificationRegistrationService;
import com.web.ddajait.service.PartQuestionService;

import io.jsonwebtoken.io.IOException;
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
    private final PartQuestionService partQuestionService;

    @GetMapping("/all")
    @Operation(summary = "모든 자격증 데이터", description = "모든 자격증 데이터를 가져오는 API 입니다.")
    public List<CertificateInfoDto> getAllCertificate() {
        log.info("[CertificateController][getAllCertificate] Starts");

        return certificateInfoService.getAllCertificate();

    }

    @GetMapping("/{certificateId}")
    @Operation(summary = "특정 자격증 정보 데이터", description = "특정 자격증 데이터를 가져오는 API 입니다.")
    public CertificateInfoDto getCertificate(@PathVariable("certificateId") Long certificateId) {
        log.info("[CertificateController][getCertificate] Starts");

        return certificateInfoService.findById(certificateId);

    }

    @GetMapping("/examcontent/{certificateId}")
    @Operation(summary = "자격증 시험 내용", description = "자격증 시험 내용 데이터를 가져오는 API 입니다.")
    public ExamList getExamcontent(@PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, IOException, JsonProcessingException {
        log.info("[CertificateController][getExamcontent] Starts");

        return certificateInfoService.getExamContent(certificateId);

    }

    @GetMapping("/examstandard/{certificateId}")
    @Operation(summary = "자격증 시험 기준", description = "자격증 시험 기준 데이터를 가져오는 API 입니다.")
    public ExamStandardJsonWrapper getExamStandard(@PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, JsonProcessingException {
        log.info("[CertificateController][getExamStandard] Starts");

        return certificateInfoService.getExamStandard(certificateId);

    }

    @GetMapping("/eligibility/{certificateId}")
    @Operation(summary = "자격증 시험 자격", description = "자격증 시험 자격 데이터를 가져오는 API 입니다.")
    public ElibilityJsonWrapper getEligibility(@PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, JsonProcessingException {
        log.info("[CertificateController][getEligibility] Starts");

        return certificateInfoService.getElibility(certificateId);

    }

    @GetMapping("/question/{certificatePartId}")
    @Operation(summary = "자격증 문제 데이터", description = "챕터별 자격증 문제 데이터를 가져오는 API 입니다.")
    public List<PartQuestionDto> getCertificateQuestion(Long certificatePartId) throws Exception {
        log.info("[CertificateController][getCertificateQuestion] Starts");

        return partQuestionService.getQuestionListbyCertificatePartID(certificatePartId);

    }

    @GetMapping("/register/all")
    @Operation(summary = "모든 자격증 접수 일정 데이터", description = "모든 자격증의 접수 일정 데이터를 가져오는 API 입니다.")
    public List<CertificationRegistrationDto> getAllCertificateRegistration() {
        log.info("[CertificateController][getAllCertificateRegistration] Starts");

        return certificationRegistrationService.getAllCerticationResgitration();

    }

    @GetMapping("/calandar")
    @Operation(summary = "캘린더 데이터", description = "캘린더 데이터를 가져오는 API 입니다.")
    public List<CalendarDto> getCalendarData() {
        log.info("[CertificateController][getCalendarData] Starts");

        return certificationRegistrationService.getCalendarContent();

    }
}
