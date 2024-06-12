package com.web.ddajait.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.PartQuestionEntity;

public interface PartQuestionRepository extends JpaRepository<PartQuestionEntity, Long> {


    List<PartQuestionEntity> findByChallengePart_ChallengePartId(Long partId);

    List<PartQuestionEntity> findByCertificatePartInfo_CertificatePartId(Long certificatePartId);

    // 챌린지 아이디별 문제 가져오기 
    List<PartQuestionEntity> findByChallengePart_ChallengeInfo_ChallengeId(Long challengeId);
    

}
