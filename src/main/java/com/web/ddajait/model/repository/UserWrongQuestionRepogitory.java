package com.web.ddajait.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.UserWrongQuestionEntity;

public interface UserWrongQuestionRepogitory extends JpaRepository<UserWrongQuestionEntity, Long> {

    // 유저별 챌린지별 틀린 문제 가져오기
    Optional<UserWrongQuestionEntity> findByUser_UserIdAndChallengeInfo_ChallengeId(Long userId,
            Long challengeId);
}
