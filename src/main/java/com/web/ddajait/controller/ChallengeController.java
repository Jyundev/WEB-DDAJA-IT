package com.web.ddajait.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.ChallegeInfo.ChallengeCardDto;
import com.web.ddajait.model.dto.ChallegeInfo.ChallengeInfoDto;
import com.web.ddajait.service.ChallengeInfoSercive;
import com.web.ddajait.service.ChallengePartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/challenge")
@Tag(name = "Challenge", description = "Challenge API")
public class ChallengeController {

    private final ChallengeInfoSercive challengeInfoSercive;
    private final ChallengePartService challengePartService;

    @Operation(summary = "모든 챌린지 데이터", description = "모든 챌린지 데이터를 가져오는 API 입니다.")
    @GetMapping("/all")
    public List<ChallengeInfoDto> getChallengeInfo() throws Exception {

        log.info("[ChallengeController][getChallengeInfo] Starts");

        return challengeInfoSercive.getAllChallengeInfo();
    }

    @Operation(summary = "특정 챌린지 데이터", description = "특정 챌린지 데이터를 가져오는 API 입니다.")
    @GetMapping("/detail/{challengeId}")
    public ChallengeInfoDto getChallengeInfoById(@PathVariable Long challengeId) throws Exception {

        log.info("[ChallengeController][getChallengeInfo] Starts");

        return challengeInfoSercive.findById(challengeId);
    }

    @Operation(summary = "모든 챌린지 챕터 데이터", description = "모든 챌린지 챕터 데이터를 가져오는 API 입니다.")
    @GetMapping("/part/all")
    public List<ChallengePartDto> getAllchallengePartInfo() throws Exception {

        log.info("[ChallengeController][getchallengePartInfo] Starts");

        return challengePartService.getAllchallengePartInfo();
    }

    @Operation(summary = "챌린지 시작 날짜순으로 정렬한 챌린지 데이터", description = "메인에 보여지는 데이터로 챌린지 시작 날짜가 가까운 챌린지 데이터 10개를 가져오는 API 입니다. ")
    @GetMapping("/recent")
    public List<ChallengeCardDto> getRecentChallenge() throws Exception {
        log.info("[ChallengeController][getRecentChallenge] Starts");

        return challengeInfoSercive.getRecentChallenges();

    }

    @Operation(summary = "HOT 챌린지 데이터", description = "메인에 보여지는 데이터로 참가자 수가 많은 챌린지 데이터 10개를 가져오는 API 입니다. ")
    @GetMapping("/hot")
    public List<ChallengeCardDto> getHOtChallenge() throws Exception {
        log.info("[ChallengeController][getHOtChallenge] Starts");

        return challengeInfoSercive.getHotChallenges();
    }

}
