package com.web.ddajait.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.ddajait.model.entity.ChallengeInfoEntity;

public interface ChallengeInfoRepository extends JpaRepository<ChallengeInfoEntity, Long>{

        
    // @Query(value = "SELECT COUNT(*) FROM challenge_info WHERE id = :id", nativeQuery = true)
    // int countMemberByMemberNickname(@Param("id") String id);

    
}
