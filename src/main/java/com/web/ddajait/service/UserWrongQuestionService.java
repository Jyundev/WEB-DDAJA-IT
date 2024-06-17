package com.web.ddajait.service;

import com.web.ddajait.model.dto.User.UserWrongQuestionDto;

public interface UserWrongQuestionService {

    public void saveWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception;

    public UserWrongQuestionDto findWrongQuestionByUserIdChallengeId(Long UserId, Long ChallengeId) throws Exception;

    public void modifyWrongQuestion(Long userId, Long challengeId, UserWrongQuestionDto userWrongQuestionDto)
            throws Exception;

}
