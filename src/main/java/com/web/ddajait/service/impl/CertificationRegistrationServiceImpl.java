package com.web.ddajait.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.CertificationRegistrationDao;
import com.web.ddajait.model.dto.CertificationRegistrationDto;
import com.web.ddajait.model.dto.Calendar.CalendarDto;
import com.web.ddajait.model.dto.Calendar.ExtendedProps;
import com.web.ddajait.service.CertificationRegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CertificationRegistrationServiceImpl implements CertificationRegistrationService {

    private final CertificationRegistrationDao certificationRegistrationDao;

    @Override
    public List<CertificationRegistrationDto> getAllCerticationResgitration() {
        log.info("[CertificationRegistrationServiceImpl][CertificationRegistrationDto] Starts");

        return certificationRegistrationDao.getAllCerticationResgitration().stream()
                .map(CertificationRegistrationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CalendarDto> getCalendarContent() {
        // 모든 CertificationRegistrationDto를 가져옴
        List<CertificationRegistrationDto> dtos = getAllCerticationResgitration();

        // 각 dto를 CalendarDto로 변환하여 리스트로 수집
        List<CalendarDto> calendarDtos = dtos.stream().map(data -> {
            CalendarDto calendarDto = new CalendarDto();

            // 시작일과 종료일 설정
            String startDay = data.getReceptionStart();
            String endDay = data.getReceptionEnd();
            String title = data.getCertificateName() + " 접수";

            calendarDto.setStart(startDay);
            calendarDto.setEnd(endDay);
            calendarDto.setTitle(title);

            // ExtendedProps 설정
            List<ExtendedProps> extendedProps = new ArrayList<>();

            // Round와 Type 설정
            ExtendedProps eProps = new ExtendedProps();
            eProps.setRound(data.getRound());
            eProps.setType(data.getType());

            extendedProps.add(eProps); // ExtendedProps 리스트에 추가

            calendarDto.setExtendedProps(extendedProps);

            return calendarDto;
        }).collect(Collectors.toList());

        return calendarDtos;
    }

}
