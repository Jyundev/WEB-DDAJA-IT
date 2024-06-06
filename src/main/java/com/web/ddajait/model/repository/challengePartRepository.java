package com.web.ddajait.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.ddajait.model.entity.ChallengePartEntity;

@Repository
public interface ChallengePartRepository extends JpaRepository<ChallengePartEntity, Long> {

    
    List<ChallengePartEntity> findByChallengeInfo_ChallengeId(Long challengeId);

    @Query(value = "SELECT * FROM challenge_part WHERE challenge_id = :challenge_id", nativeQuery = true)
    List<ChallengePartEntity> findChallengePartsByChallengeId(@Param("challenge_id") Long challenge_id);

}