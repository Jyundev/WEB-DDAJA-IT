package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.ChallengePart.Challenge;

public interface ChallengePartService {

    public List<ChallengePartDto> getAllchallengePartInfo();

    public ChallengePartDto getchallengePartInfo(Long challengePartId);

    public Challenge getChallengersDetailData(Long challengeId);
}
