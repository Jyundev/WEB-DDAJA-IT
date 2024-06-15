package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.dto.UserChallenge.TotalUserDto;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserchallengeDao {

    // 데이터 조회
    public List<UserChallengeEntity> findUserChallengeByUserId(Long userId);

    public UserChallengeEntity findUserChallengeById(Long id);

    // 데이터 찾기
    public UserChallengeEntity findUserChallengeId(ChallengeInfoEntity challengeInfoEntity, UserEntity userEntity);

    // 데이터 찾기
    public Optional<UserChallengeEntity> findByUserIdChallengeId(Long userId, Long challengeId);

    // 데이터 업데이트
    public void updateUserChallenge(UserChallengeEntity entity);

    // 데이터 추가
    public void insertUserChallenge(UserChallengeEntity entity);

    // 챌린지별 참여 유저 수
    public int countMemberByChallengeId(Long challengeId);

    // 챌린지 평균 진행률
    public double getTotalProgress(Long challengeId);

    // 챌린지 신청
    public void subChallenge(UserChallengeEntity entity);

    // 챌린지별 참가자 수
    public List<TotalUserDto> getTotalUser();

}
