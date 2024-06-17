package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.ChallengePartEntity;

public interface ChallengePartDao {

    public List<ChallengePartEntity> getAllChallenge();

    public Optional<ChallengePartEntity> findChallengeById(Long challengePartId);

    public Optional<List<ChallengePartEntity>> findChallengePartsByCertificateId(Long certificateID);

}
