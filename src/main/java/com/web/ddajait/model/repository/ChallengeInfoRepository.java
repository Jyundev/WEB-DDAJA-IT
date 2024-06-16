package com.web.ddajait.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.ddajait.model.entity.ChallengeInfoEntity;

public interface ChallengeInfoRepository extends JpaRepository<ChallengeInfoEntity, Long> {

    @Query(value = "SELECT * FROM challenge_info WHERE start_day  >= CURDATE() ORDER BY start_day LIMIT 10;", nativeQuery = true)
    List<ChallengeInfoEntity> getRecentChallenges();

    @Query(value = "SELECT * FROM challenge_info;", nativeQuery = true)
    List<ChallengeInfoEntity> getAllChallenges();

}
