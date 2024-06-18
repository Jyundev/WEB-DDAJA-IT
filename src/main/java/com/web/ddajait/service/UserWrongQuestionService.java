package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.User.UserWrongQuestionDto;

public interface UserWrongQuestionService {

    public void saveWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception;

    public List<UserWrongQuestionDto> findWrongQuestionByUserIdChallengeId(Long UserId, Long ChallengeId)
            throws Exception;

    public void modifyWrongQuestion(Long userId, Long challengeId, int step, UserWrongQuestionDto userWrongQuestionDto)
            throws Exception;

}
