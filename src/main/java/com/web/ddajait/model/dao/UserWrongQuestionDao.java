package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.UserWrongQuestionEntity;

public interface UserWrongQuestionDao {

    public void saveWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception;

    public Optional<List<UserWrongQuestionEntity>> findWrongQuestionList(Long UserId, Long ChallengeId)
            throws Exception;

    public Optional<UserWrongQuestionEntity> findWrongQuestionByQuestionId(Long questionId)
            throws Exception;

    public void modifyWrongQuestion(UserWrongQuestionEntity userWrongQuestionEntity) throws Exception;
}
