package com.web.ddajait.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.challengePartDao;
import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.model.repository.challengePartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class challengePartImpl implements challengePartDao{

    private final challengePartRepository challengePartRepository;
    
    @Override
    public List<ChallengePartEntity> getAllCertificate() {

        return challengePartRepository.findAll();
    }
    
       
}
