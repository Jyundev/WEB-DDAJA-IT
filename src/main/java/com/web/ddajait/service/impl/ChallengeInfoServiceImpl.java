package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.UserchallengeDao;
import com.web.ddajait.model.dto.ChallegeInfo.ChallengeCardDto;
import com.web.ddajait.model.dto.ChallegeInfo.ChallengeInfoDto;
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
    private final UserchallengeDao userchallengeDao;

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

    @Override
    public List<ChallengeCardDto> getRecentChallenges() throws Exception {
        List<ChallengeInfoEntity> entitys = challengeInfoDao.getRecentChallegnges();
        return entitys.parallelStream().map(
                ChallengeCardDto::from).collect(Collectors.toList());
    }

    @Override
    public List<ChallengeCardDto> getHotChallenges() throws Exception {
        log.info("[ChallengeInfoServiceImpl][ChallengeCardDto] Starts");

        List<ChallengeCardDto> cardDtos = userchallengeDao.getTotalUser().parallelStream().map(
                entity -> {
                    ChallengeInfoEntity challengeInfoEntity = challengeInfoDao.findById(entity.getChallenge_id()).get();
                    log.info("[ChallengeInfoServiceImpl][getHotChallenges] challengeId {}", entity.getChallenge_id());
                    return ChallengeCardDto.from(challengeInfoEntity);

                }).collect(Collectors.toList());

        return cardDtos;
    }

}
