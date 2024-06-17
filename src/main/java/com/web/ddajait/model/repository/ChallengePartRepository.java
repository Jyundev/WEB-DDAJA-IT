package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.ddajait.model.entity.ChallengePartEntity;

@Repository
public interface ChallengePartRepository extends JpaRepository<ChallengePartEntity, Long> {

    Optional<List<ChallengePartEntity>> findChallengePartByCertificateInfo_CertificateId(Long challengeId);

    // @Query(value = "SELECT * FROM challenge_part WHERE certificateId = ?1",
    // nativeQuery = true)
    // List<ChallengePartEntity> findChallengePartsByCertificateId( Long
    // certificateId);

}