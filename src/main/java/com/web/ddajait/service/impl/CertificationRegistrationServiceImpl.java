package com.web.ddajait.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.ddajait.model.dao.CertificationRegistrationDao;
import com.web.ddajait.model.dto.Calendar.CalendarDto;
import com.web.ddajait.model.dto.Calendar.ExtendedProps;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityJsonWrapper;
import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityStandard;
import com.web.ddajait.model.dto.CertificateRegister.CertificationRegistrationDto;
import com.web.ddajait.service.CertificateInfoService;
import com.web.ddajait.service.CertificationRegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CertificationRegistrationServiceImpl implements CertificationRegistrationService {

    private final CertificationRegistrationDao certificationRegistrationDao;
    private final CertificateInfoService certificateInfoService;
    // private final CertificateInfoDao certificateInfoDao;

    @Override
    public List<CertificationRegistrationDto> getAllCerticationResgitration() {
        log.info("[CertificationRegistrationServiceImpl][CertificationRegistrationDto] Starts");

        return certificationRegistrationDao.getAllCerticationResgitration().stream()
                .map(CertificationRegistrationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CalendarDto> getCalendarContent() {
        log.info("[CertificationRegistrationServiceImpl][getCalendarContent] Starts");
        // 모든 CertificationRegistrationDto를 가져옴
        List<CertificationRegistrationDto> dtos = getAllCerticationResgitration();

        // 각 dto를 CalendarDto로 변환하여 리스트로 수집
        // stram -> parallelStream 병렬처리 
        List<CalendarDto> calendarDtos = dtos.parallelStream().map(data -> {
            CertificateInfoDto certificateInfoDto = certificateInfoService.findById(data.getCertificateId());
            List<ElibilityStandard> examStandard = new ArrayList<>();

            try {
                ElibilityJsonWrapper wJsonWrapper = certificateInfoService.getElibility(data.getCertificateId());

                if (wJsonWrapper.getStandard() != null) {
                    examStandard = wJsonWrapper.getStandard();
                } else {
                    // simpleSubject
                    ElibilityStandard standard = new ElibilityStandard();
                    String simple = wJsonWrapper.getSimplestandard();
                    standard.setCondition(new ArrayList<>());
                    standard.setQualification(simple);
                    examStandard.add(standard);
                }

            } catch (JsonProcessingException e) {
                log.info("[CertificationRegistrationServiceImpl][getCalendarContent] JsonProcessingException ", data);
            }
            CalendarDto calendarDto = new CalendarDto();

            // 시작일과 종료일 설정
            String startDay = data.getReceptionStart();
            String endDay = data.getReceptionEnd();
            
            String title = data.getCertificateName() + " 접수";

            Long certificateId = data.getCertificateId();

            calendarDto.setStartDay(startDay);
            calendarDto.setEndDay(endDay);
            calendarDto.setEndDay(endDay);
            calendarDto.setTitle(title);
            calendarDto.setCertificateId(certificateId);

            // ExtendedProps 설정
            List<ExtendedProps> extendedProps = new ArrayList<>();

            // Round와 Type 설정
            ExtendedProps eProps = new ExtendedProps();
            eProps.setRound(data.getRound());
            eProps.setType(data.getType());
            eProps.setTestDay(data.getTestDay());

            // 시험에 대한 설명
            String overView = certificateInfoDto.getOverview();
            eProps.setOverView(overView);
            // 자격증 시험 자격
            eProps.setStandards(examStandard);

            extendedProps.add(eProps); // ExtendedProps 리스트에 추가

            calendarDto.setExtendedProps(extendedProps);

            return calendarDto;
        }).collect(Collectors.toList());

        return calendarDtos;
    }

}
