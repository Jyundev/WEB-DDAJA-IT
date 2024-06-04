package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.CertificationRegistrationDao;
import com.web.ddajait.model.dto.CertificationRegistrationDto;
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

}
