package com.web.ddajait.model.dao;

import java.util.List;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserchallengeDao {

    // 데이터 조회
    public List<UserChallengeEntity> findUserChallengeByUserId(Long userId);

    public UserChallengeEntity findUserChallengeById(Long id);

    // 데이터 찾기
    public UserChallengeEntity findUserChallengeId(ChallengeInfoEntity challengeInfoEntity, UserEntity userEntity);

    // 데이터 업데이트
    public void updateUserChallenge(UserChallengeEntity entity);

    // 데이터 추가
    public void insertUserChallenge(UserChallengeEntity entity);

}
