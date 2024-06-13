package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengePartDao;
import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.model.repository.ChallengePartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
    public List<ChallengePartEntity> findChallengePartsByCertificateId(Long certificateId) {
        return challengePartRepository.findByCertificateInfo_CertificateId(certificateId);
        // return challengePartRepository.findChallengePartsByChallengeId(challengeId);
        
    }

}
