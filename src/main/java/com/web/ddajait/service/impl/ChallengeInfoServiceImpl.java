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
import com.web.ddajait.util.EntityUtil;

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
        List<ChallengeInfoDto> dInfoDtos = entitys.stream().map(ChallengeInfoDto::from).collect(Collectors.toList());
        return dInfoDtos.parallelStream().map(data -> {
            ChallengeCardDto cardDto = new ChallengeCardDto();
            EntityUtil.copyNonNullProperties(data, cardDto);
            return cardDto;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ChallengeCardDto> getHotChallenges() throws Exception {
        log.info("[ChallengeInfoServiceImpl][ChallengeCardDto] Starts");
        List<ChallengeCardDto> cardDtos = userchallengeDao.getTotalUser().parallelStream().map(
                entity -> {
                    ChallengeCardDto cardDto = new ChallengeCardDto();
                    try {
                        ChallengeInfoDto challengeDto = findById(entity.getChallenge_id());
                        EntityUtil.copyNonNullProperties(challengeDto, cardDto);
                        cardDto.setTotalUser(entity.getTotal_user());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return cardDto;

                }).collect(Collectors.toList());

        return cardDtos;
    }

}
