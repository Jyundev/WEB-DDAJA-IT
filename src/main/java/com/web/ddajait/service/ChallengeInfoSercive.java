package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.ChallengeInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;

public interface ChallengeInfoSercive {

    public List<ChallengeInfoDto> getAllChallengeInfo();

    public ChallengeInfoDto findById(Long id) throws Exception;


}
