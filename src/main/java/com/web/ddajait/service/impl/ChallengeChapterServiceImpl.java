package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeChapterDao;
import com.web.ddajait.model.dto.ChallengeChapterDto;
import com.web.ddajait.service.ChallengeChapterService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ChallengeChapterServiceImpl implements ChallengeChapterService {

    final private ChallengeChapterDao challengeChapterDao;

    @Override
    public List<ChallengeChapterDto> getAllChallengeChapterInfo() {
        log.info("[ChallengeChapterServiceImpl][getAllChallengeChapterInfo] Starts");
        log.info(
                "[ChallengeChapterServiceImpl][getAllChallengeChapterInfo] " + challengeChapterDao.getAllCertificate());
        return challengeChapterDao.getAllCertificate().stream()
                .map(ChallengeChapterDto::from)
                .collect(Collectors.toList());

    }
}
