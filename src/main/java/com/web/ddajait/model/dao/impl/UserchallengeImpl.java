package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.UserchallengeDao;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserChallengeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserchallengeImpl implements UserchallengeDao {
    private final UserChallengeRepository userChallengeRepository;

    @Override
    public List<UserChallengeEntity> findUserChallengeByUserId(Long id) {
        return userChallengeRepository.findByUser_UserId(id);
    }

    @Override
    public void updateUserChallenge(UserChallengeEntity entity) {
        userChallengeRepository.save(entity);
    }

    @Override
    public void insertUserChallenge(UserChallengeEntity entity) {
        userChallengeRepository.save(entity);

    }

    @Override
    public UserChallengeEntity findUserChallengeById(Long id) {

        return userChallengeRepository.findById(id).get();
    }

    @Override
    public UserChallengeEntity findUserChallengeId(ChallengeInfoEntity challengeInfoEntity, UserEntity userEntity) {
        return userChallengeRepository.findByUserAndChallengeInfo(userEntity, challengeInfoEntity).orElse(null);
    }

    @Override
    public Optional<UserChallengeEntity> findByUserIdChallengeId(Long userId, Long challengeId) {

        return userChallengeRepository.findByUser_UserIdAndChallengeInfo_ChallengeId(userId, challengeId);
    }

    @Override
    public int countMemberByChallengeId(Long challengeId) {
        return userChallengeRepository.countMemberByChallengeId(challengeId);
    }

    @Override
    public double getTotalProgress(Long challengeId) {
        return userChallengeRepository.getTotalProgress(challengeId);
    }

    @Override
    public void subChallenge(UserChallengeEntity entity) {
        userChallengeRepository.save(entity);

    }

}
