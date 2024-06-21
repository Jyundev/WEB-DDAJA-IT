package com.web.ddajait.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.Calendar.CalendarDto;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityJsonWrapper;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;
import com.web.ddajait.model.dto.CertificateInfo.ExamStandard.ExamStandardJsonWrapper;
import com.web.ddajait.model.dto.CertificateRegister.CertificationRegistrationDto;
import com.web.ddajait.model.dto.ChallengePart.PartQuestionDto;
import com.web.ddajait.model.dto.Response.ResponseDto;
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
    public ResponseEntity<ResponseDto<List<CertificateInfoDto>>> getAllCertificate() {
        log.info("[CertificateController][getAllCertificate] Starts");

        return ResponseHandler.SUCCESS(certificateInfoService.getAllCertificate(), "모든 자격증 데이터 가져오기 성공");

    }

    @GetMapping("/{certificateId}")
    @Operation(summary = "특정 자격증 정보 데이터", description = "특정 자격증 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<CertificateInfoDto>> getCertificate(
            @PathVariable("certificateId") Long certificateId) {
        log.info("[CertificateController][getCertificate] Starts");

        return ResponseHandler.SUCCESS(certificateInfoService.findById(certificateId),
                String.format("certificateId %d  자격증 데이터를 가져오기 성공", certificateId));

    }

    @GetMapping("/examcontent/{certificateId}")
    @Operation(summary = "자격증 시험 내용", description = "자격증 시험 내용 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<ExamList>> getExamcontent(@PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, IOException, JsonProcessingException {
        log.info("[CertificateController][getExamcontent] Starts");

        return ResponseHandler.SUCCESS(certificateInfoService.getExamContent(certificateId),
                String.format("%d 자격증 시험 내용 데이터를 가져오기 성공", certificateId));

    }

    @GetMapping("/examstandard/{certificateId}")
    @Operation(summary = "자격증 시험 기준", description = "자격증 시험 기준 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<ExamStandardJsonWrapper>> getExamStandard(
            @PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, JsonProcessingException {
        log.info("[CertificateController][getExamStandard] Starts");

        return ResponseHandler.SUCCESS(certificateInfoService.getExamStandard(certificateId), "자격증 시험 기준 데이터 가져오기 성공");

    }

    @GetMapping("/eligibility/{certificateId}")
    @Operation(summary = "자격증 시험 자격", description = "자격증 시험 자격 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<ElibilityJsonWrapper>> getEligibility(
            @PathVariable("certificateId") Long certificateId)
            throws JsonMappingException, JsonProcessingException {
        log.info("[CertificateController][getEligibility] Starts");

        return ResponseHandler.SUCCESS(certificateInfoService.getElibility(certificateId), "자격증 시험 자격 데이터 가져오기 성공");

    }

    @GetMapping("/question/{certificatePartId}")
    @Operation(summary = "자격증 문제 데이터", description = "챕터별 자격증 문제 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<List<PartQuestionDto>>> getCertificateQuestion(Long certificatePartId)
            throws Exception {
        log.info("[CertificateController][getCertificateQuestion] Starts");

        return ResponseHandler.SUCCESS(partQuestionService.getQuestionListbyCertificatePartID(certificatePartId),
                "챕터별 자격증 문제 데이터를 가져오기 성공");

    }

    @GetMapping("/registration")
    @Operation(summary = "모든 자격증 접수 일정 데이터", description = "모든 자격증의 접수 일정 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<List<CertificationRegistrationDto>>> getAllCertificateRegistration() {
        log.info("[CertificateController][getAllCertificateRegistration] Starts");

        return ResponseHandler.SUCCESS(certificationRegistrationService.getAllCerticationResgitration(),
                "모든 자격증의 접수 일정 데이터 가져오기 성공");

    }

    @GetMapping("/calandar")
    @Operation(summary = "캘린더 데이터", description = "캘린더 데이터를 가져오는 API 입니다.")
    public ResponseEntity<ResponseDto<List<CalendarDto>>> getCalendarData() {
        log.info("[CertificateController][getCalendarData] Starts");

        return ResponseHandler.SUCCESS(certificationRegistrationService.getCalendarContent(), "캘린더 데이터 가져오기 성공");

    }
}
