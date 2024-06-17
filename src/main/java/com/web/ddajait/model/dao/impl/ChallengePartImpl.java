package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengePartDao;
import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.model.repository.ChallengePartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengePartImpl implements ChallengePartDao {

    final private ChallengePartRepository challengePartRepository;

    @Override
    public List<ChallengePartEntity> getAllChallenge() {

        return challengePartRepository.findAll();
    }

    @Override
    public Optional<ChallengePartEntity> findChallengeById(Long challengePartId) {

        return challengePartRepository.findById(challengePartId);
    }

    @Override
    public Optional<List<ChallengePartEntity>> findChallengePartsByCertificateId(Long certificateId) {
        log.info("[ChallengePartImpl][findChallengePartsByCertificateId] certificateId : "+certificateId);
        
        return challengePartRepository.findChallengePartByCertificateInfo_CertificateId(certificateId);
        
    }

}
