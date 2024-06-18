package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.config.error.custom.WrongQuestionNotFoundException;
import com.web.ddajait.model.dao.UserWrongQuestionDao;
import com.web.ddajait.model.entity.UserWrongQuestionEntity;
import com.web.ddajait.model.repository.UserWrongQuestionRepogitory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserWrongQuetionImpl implements UserWrongQuestionDao {

    private final UserWrongQuestionRepogitory userWrongQuestionRepogitory;

    @Override
    public void modifyWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception {
        userWrongQuestionRepogitory.save(userWrongQuestionEntity);
    }

    @Override
    public void saveWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception {
        userWrongQuestionRepogitory.save(userWrongQuestionEntity);
    }

    @Override
    public Optional<List<UserWrongQuestionEntity>> findWrongQuestionByUserIdChallengeId(Long userId, Long challengeId)
            throws Exception, WrongQuestionNotFoundException {
        Optional<List<UserWrongQuestionEntity>> userWrongQuestionEntity = userWrongQuestionRepogitory
                .findByUser_UserIdAndChallengeInfo_ChallengeId(userId, challengeId);

        if (userWrongQuestionEntity.isPresent()) {
            return userWrongQuestionEntity;
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<UserWrongQuestionEntity> findWrongQuestionByStep(Long userId, Long challengeId, int step)
            throws Exception, WrongQuestionNotFoundException {
        Optional<UserWrongQuestionEntity> userWrongQuestionEntity = userWrongQuestionRepogitory
                .findByStepAndUser_UserIdAndChallengeInfo_ChallengeId(userId, challengeId, step);

        return userWrongQuestionEntity;
    }

}
