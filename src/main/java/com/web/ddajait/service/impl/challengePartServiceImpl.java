package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.challengePartDao;
import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.service.ChallengePartService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class challengePartServiceImpl implements ChallengePartService {

    final private challengePartDao challengePartDao;

    @Override
    public List<ChallengePartDto> getAllchallengePartInfo() {
        log.info("[challengePartServiceImpl][getAllchallengePartInfo] Starts");
        log.info(
                "[challengePartServiceImpl][getAllchallengePartInfo] " + challengePartDao.getAllCertificate());
        return challengePartDao.getAllCertificate().stream()
                .map(ChallengePartDto::from)
                .collect(Collectors.toList());

    }
}
