package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.config.error.custom.WrongQuestionNotFoundException;
import com.web.ddajait.model.entity.UserWrongQuestionEntity;

public interface UserWrongQuestionDao {

        public void saveWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception;

        public Optional<List<UserWrongQuestionEntity>> findWrongQuestionByUserIdChallengeId(Long userId,
                        Long challengeId)
                        throws Exception, WrongQuestionNotFoundException;

        public Optional<UserWrongQuestionEntity> findWrongQuestionByStep(Long userId, Long challengeId, int step)
                        throws Exception, WrongQuestionNotFoundException;

        public void modifyWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception;
}
