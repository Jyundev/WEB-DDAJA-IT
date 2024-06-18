package com.web.ddajait.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.config.error.custom.WrongQuestionNotFoundException;
import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dao.UserWrongQuestionDao;
import com.web.ddajait.model.dto.User.UserWrongQuestionDto;
import com.web.ddajait.model.entity.UserWrongQuestionEntity;
import com.web.ddajait.service.UserWrongQuestionService;
import com.web.ddajait.util.EntityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserWrongQuestionServiceImpl implements UserWrongQuestionService {

    private final UserWrongQuestionDao userWrongQuestionDao;
    private final UserDao userDao;
    private final ChallengeInfoDao challengeInfoDao;

    @Override
    public void modifyWrongQuestion(Long userId, Long challengeId, int step, UserWrongQuestionDto userWrongQuestionDto)
            throws Exception {
        log.info("[UserWrongQuestionServiceImpl][modifyWrongQuestion] Starts");

        Optional<UserWrongQuestionEntity> wrongQuestionEntity = userWrongQuestionDao
                .findWrongQuestionByStep(userId, challengeId, step);

        if (wrongQuestionEntity.isPresent()) {

            UserWrongQuestionEntity wEntity = wrongQuestionEntity.get();
            EntityUtil.copyNonNullProperties(userWrongQuestionDto, wEntity);


            wEntity.setUser(userDao.findById(userWrongQuestionDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));


        userWrongQuestionDao.saveWrongQuestion(wEntity);
         
        } else {
            saveWrongQuestion(userWrongQuestionDto);
        }
    }

    @Override
    public void saveWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][saveWrongQuestion] Starts");
        UserWrongQuestionEntity userWrongQuestionEntity = new UserWrongQuestionEntity();
        EntityUtil.copyNonNullProperties(userWrongQuestionDto, userWrongQuestionEntity);
        userWrongQuestionEntity
                .setChallengeInfo(challengeInfoDao.findById(userWrongQuestionDto.getChallengeId()).orElseThrow(() -> new RuntimeException("Challenge not found")));
        userWrongQuestionEntity.setUser(userDao.findById(userWrongQuestionDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        ;
        userWrongQuestionDao.saveWrongQuestion(userWrongQuestionEntity);
    }

    @Override
    public List<UserWrongQuestionDto> findWrongQuestionByUserIdChallengeId(Long userId, Long challengeId) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][findWrongQuestionByUserIdChallengeId] Starts");
    
        // Validate input parameters
        if (userId == null || challengeId == null) {
            throw new IllegalArgumentException("UserId and ChallengeId must not be null");
        }
    
        try {
            Optional<List<UserWrongQuestionEntity>> userWrongQuestionEntities = userWrongQuestionDao
                    .findWrongQuestionByUserIdChallengeId(userId, challengeId);
    
            if (userWrongQuestionEntities.isPresent()) {
                return userWrongQuestionEntities.get().stream()
                        .map(UserWrongQuestionDto::from)
                        .collect(Collectors.toList());
            } else {
                log.warn("[UserWrongQuestionServiceImpl][findWrongQuestionByUserIdChallengeId] No wrong question found for userId: {}, challengeId: {}", userId, challengeId);
                throw new WrongQuestionNotFoundException(
                        "No wrong question found for userId: " + userId + " and challengeId: " + challengeId);
            }
        } catch (Exception e) {
            log.error("[UserWrongQuestionServiceImpl][findWrongQuestionByUserIdChallengeId] Exception occurred while fetching data", e);
            throw e; // or wrap in a custom service exception
        }
    }
    
    
    

}
