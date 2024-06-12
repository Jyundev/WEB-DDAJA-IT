package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.UserWrongQuestionEntity;

public interface UserWrongQuestionRepogitory extends JpaRepository<UserWrongQuestionEntity, Long> {

    // 문제 기준 오답 문제 찾기
    Optional<UserWrongQuestionEntity> findByPartQuestion_QuestionId(Long questionId);

    // 유저별 챌린지별 틀린 문제 가져오기
    Optional<List<UserWrongQuestionEntity>> findByUser_UserIdAndChallengeInfo_ChallengeId(Long userId,
            Long challengeId);
}
