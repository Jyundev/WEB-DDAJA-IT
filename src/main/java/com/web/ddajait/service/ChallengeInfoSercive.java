package com.web.ddajait.service;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.dto.ChallengeInfoDto;

public interface ChallengeInfoSercive {

    public List<ChallengeInfoDto> getAllChallengeInfo();

    public Optional<ChallengeInfoDto> findById(Long id) throws Exception;

    
}
