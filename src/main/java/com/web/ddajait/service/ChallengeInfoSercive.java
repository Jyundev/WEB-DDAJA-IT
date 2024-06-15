package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.ChallegeInfo.ChallengeCardDto;
import com.web.ddajait.model.dto.ChallegeInfo.ChallengeInfoDto;

public interface ChallengeInfoSercive {

    public List<ChallengeInfoDto> getAllChallengeInfo();

    public ChallengeInfoDto findById(Long id) throws Exception;

    // 메인 페이지에 접수일이 가까운 자격증 챌린지 가져오기
    public List<ChallengeCardDto> getRecentChallenges() throws Exception;

    //메인 페이지에 참가자수가 많은 챌린지 가져오기 
    public List<ChallengeCardDto> getHotChallenges() throws Exception;

}
