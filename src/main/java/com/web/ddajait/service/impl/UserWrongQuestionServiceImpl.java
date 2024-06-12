package com.web.ddajait.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.PartQuestionDao;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dao.UserWrongQuestionDao;
import com.web.ddajait.model.dto.UserWrongQuestionDto;
import com.web.ddajait.model.entity.UserWrongQuestionEntity;
import com.web.ddajait.service.UserWrongQuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserWrongQuestionServiceImpl implements UserWrongQuestionService {

    private final UserWrongQuestionDao userWrongQuestionDao;
    private final UserDao userDao;
    private final ChallengeInfoDao challengeInfoDao;
    private final PartQuestionDao partQuestionDao;

    @Override
    public List<UserWrongQuestionDto> findWrongQuestionList(Long UserId, Long ChallengeId) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][findWrongQuestion] Starts");
        Optional<List<UserWrongQuestionEntity>> wrongQuestions = userWrongQuestionDao.findWrongQuestionList(UserId,
                ChallengeId);
        return wrongQuestions.map(list -> list.stream()
                .map(UserWrongQuestionDto::from)
                .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);

    }

    @Override
    public void modifyWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][modifyWrongQuestion] Starts");

        Optional<UserWrongQuestionEntity> wrongQuestionEntity = userWrongQuestionDao
                .findWrongQuestionByQuestionId(userWrongQuestionDto.getQuestionId());
        if (wrongQuestionEntity.isPresent()) {
            UserWrongQuestionEntity wEntity = wrongQuestionEntity.get();
        } else {
        }
    }

    @Override
    public void saveWrongQuestion(UserWrongQuestionDto userWrongQuestionDto) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][saveWrongQuestion] Starts");
        UserWrongQuestionEntity userWrongQuestionEntity = new UserWrongQuestionEntity();
        userWrongQuestionEntity
                .setChallengeInfo(challengeInfoDao.findById(userWrongQuestionDto.getChallengeId()).get());
        userWrongQuestionEntity.setPartQuestion(partQuestionDao.findById(userWrongQuestionDto.getQuestionId()).get());
        userWrongQuestionEntity.setUser(userDao.findById(userWrongQuestionDto.getUserId()).get());

        userWrongQuestionDao.saveWrongQuestion(userWrongQuestionEntity);

    }

    @Override
    public Optional<UserWrongQuestionDto> findWrongQuestionByQuestionId(Long questionId) throws Exception {
        log.info("[UserWrongQuestionServiceImpl][findWrongQuestionByQuestionId] Starts");
        Optional<UserWrongQuestionEntity> userWrongEntity = userWrongQuestionDao
                .findWrongQuestionByQuestionId(questionId);

                return null;
    }
}
