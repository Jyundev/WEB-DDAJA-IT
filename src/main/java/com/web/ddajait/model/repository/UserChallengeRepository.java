package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserChallengeRepository extends JpaRepository<UserChallengeEntity, Long> {

    public List<UserChallengeEntity> findByUser_UserId(Long userId);

    // user_challenge_id 찾기
    // @Query(value = "SELECT user_challenge_id FROM user_certificate WHERE user_id
    // = :user_id AND challenge_id = :challenge_id", nativeQuery = true)
    // Optional<Long> findUserChallengeByUserIdAndCertificateId(@Param("user_id")
    // Long userId, @Param("challenge_id") Long challenge_id);

    Optional<UserChallengeEntity> findByUserAndChallengeInfo(UserEntity user, ChallengeInfoEntity challenge);

    Optional<UserChallengeEntity> findByUser_UserIdAndChallengeInfo_ChallengeId(Long userId, Long challengeID);

    @Query(value = "SELECT COUNT(*) FROM user_challenge WHERE challenge_id = :challenge_id", nativeQuery = true)
    int countMemberByChallengeId(@Param("challenge_id") Long challenge_id);

    @Query(value = "SELECT COALESCE(AVG(progress_rate), 0) FROM user_challenge WHERE challenge_id = :challenge_id", nativeQuery = true)
    double getTotalProgress(@Param("challenge_id") Long challenge_id);

}