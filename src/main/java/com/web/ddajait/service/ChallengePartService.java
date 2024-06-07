package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.model.dto.ChallengePart.Certificate;

public interface ChallengePartService {

    public List<ChallengePartDto> getAllchallengePartInfo();

    public ChallengePartDto getchallengePartInfo(Long challengePartId);

    public Certificate getChallengePart(Long challengeId);

    public List<PartQuestionDto> getPartQuestionDtos(Long partId);

}
