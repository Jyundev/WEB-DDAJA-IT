package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public Optional<UserWrongQuestionEntity> findWrongQuestionByQuestionId(Long questionId) throws Exception {
        return userWrongQuestionRepogitory.findByPartQuestion_QuestionId(questionId);
    }

    @Override
    public Optional<List<UserWrongQuestionEntity>> findWrongQuestionList(Long UserId, Long ChallengeId)
            throws Exception {

        return userWrongQuestionRepogitory.findByUser_UserIdAndChallengeInfo_ChallengeId(UserId, ChallengeId);
    }

}
