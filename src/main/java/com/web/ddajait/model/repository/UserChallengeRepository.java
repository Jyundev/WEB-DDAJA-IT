package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.ddajait.model.dto.User.UserChallenge.TotalUserDto;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserChallengeRepository extends JpaRepository<UserChallengeEntity, Long> {

    public List<UserChallengeEntity> findByUser_UserId(Long userId);

    Optional<UserChallengeEntity> findByUserAndChallengeInfo(UserEntity user, ChallengeInfoEntity challenge);

    Optional<UserChallengeEntity> findByUser_UserIdAndChallengeInfo_ChallengeId(Long userId, Long challengeID);

    @Query(value = "SELECT COUNT(*) FROM user_challenge WHERE challenge_id = ?1", nativeQuery = true)
    int countMemberByChallengeId(Long challenge_id);

    @Query(value = "SELECT COALESCE(AVG(progress_rate), 0) FROM user_challenge WHERE challenge_id = ?1", nativeQuery = true)
    double getTotalProgress( Long challenge_id);

    // 네이티브 쿼리를 위한 커스텀 메서드
    @Query(value = "SELECT challenge_id, COUNT(*) as total_user FROM user_challenge GROUP BY challenge_id ORDER BY total_user DESC LIMIT 10;", nativeQuery = true)
    List<Object[]> getTotalUserNative();

    // Object[]를 TotalUserDto로 변환
    default List<TotalUserDto> getTotalUser() {
        List<Object[]> results = getTotalUserNative();
        return results.stream()
                .map(row -> new TotalUserDto((Long) row[0], (Long) row[1]))
                .toList();
    }
}