package com.web.ddajait.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeChapterDao;
import com.web.ddajait.model.entity.ChallengeChapterEntity;
import com.web.ddajait.model.repository.ChallengeChapterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChallengeChapterImpl implements ChallengeChapterDao{

    private final ChallengeChapterRepository challengeChapterRepository;
    
    @Override
    public List<ChallengeChapterEntity> getAllCertificate() {

        return challengeChapterRepository.findAll();
    }
    
       
}
