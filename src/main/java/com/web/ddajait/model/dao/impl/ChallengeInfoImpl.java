package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.repository.ChallengeInfoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChallengeInfoImpl implements ChallengeInfoDao {

    final private ChallengeInfoRepository challengeInfoRepository;

    @Override
    public List<ChallengeInfoEntity> getAllChallengeInfo() {
        // return challengeInfoRepository.findAll();
        return challengeInfoRepository.getAllChallenges();
    }

    @Override
    public Optional<ChallengeInfoEntity> findById(Long id) {

        return challengeInfoRepository.findById(id);
    }

    @Override
    public List<ChallengeInfoEntity> getRecentChallegnges() {
        return challengeInfoRepository.getRecentChallenges();
    }

}
