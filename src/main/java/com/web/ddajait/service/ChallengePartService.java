package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.config.error.custom.WrongQuestionNotFoundException;
import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.ChallengePart.Challenge;

public interface ChallengePartService {

    public List<ChallengePartDto> getAllchallengePartInfo() throws Exception;

    public ChallengePartDto getchallengePartInfo(Long challengePartId) throws Exception;

    public Challenge getChallengersDetailData(Long UserId, Long certificateId)
            throws Exception, WrongQuestionNotFoundException;
}
