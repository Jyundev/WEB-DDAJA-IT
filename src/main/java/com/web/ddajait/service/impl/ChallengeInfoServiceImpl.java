package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dto.ChallengeInfoDto;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.service.ChallengeInfoSercive;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ChallengeInfoServiceImpl implements ChallengeInfoSercive {

    private final ChallengeInfoDao challengeInfoDao;

    @Override
    public List<ChallengeInfoDto> getAllChallengeInfo() {
        return challengeInfoDao.getAllChallengeInfo().stream()
                .map(ChallengeInfoDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public ChallengeInfoDto findById(Long id) throws Exception {
        if (challengeInfoDao.findById(id).isPresent()) {
            ChallengeInfoEntity entity = challengeInfoDao.findById(id).get();
            return ChallengeInfoDto.from(entity);
        } else {
            throw new EntityNotFoundException("Not found ChallengeInfoEntity");
        }

    }

}
