package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.UserWrongQuestionDto;

public interface UserWrongQuestionService {

    public void saveWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception;

    public List<UserWrongQuestionDto> findWrongQuestionList(Long UserId, Long ChallengeId) throws Exception;

    public void modifyWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception;

    public UserWrongQuestionDto findWrongQuestionByQuestionId(Long questionId) throws Exception;

}
