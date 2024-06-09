package com.web.ddajait.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.ChallengeInfoDto;
import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.ChallengePart.Certificate;
import com.web.ddajait.model.dto.ChallengePart.Challenge;
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

    @Operation(summary = "모든 챌린지 챕터 데이터", description = "모든 챌린지 챕터 데이터를 가져오는 API 입니다.")
    @GetMapping("/part/all")
    public List<ChallengePartDto> getAllchallengePartInfo() throws Exception {

        log.info("[ChallengeController][getchallengePartInfo] Starts");

        return challengePartService.getAllchallengePartInfo();
    }

    @Operation(summary = "챌린지 챕터 데이터", description = "특정 챌린지 챕터 데이터를 가져오는 API 입니다.")
    @GetMapping("/part/{challengeId}")
    public Certificate getchallengePartInfo(@PathVariable("challengeId") Long challengeId) throws Exception {

        log.info("[ChallengeController][getchallengePartInfo] Starts");

        return challengePartService.getChallengePart(challengeId);
    }

    @Operation(summary = "챌린지 상세 페이지 데이터", description = "챌린지 상세 페이지 데이터를 가져오는 API 입니다. \n*현제 데이터 오류로 challengeId = 7, 8, 9 테스트 가능")
    @GetMapping("/detail/{challengeId}")
    public Challenge getchallengeDetailPageInfo(@PathVariable("challengeId") Long challengeId) throws Exception {

        log.info("[ChallengeController][getchallengeDetailPageInfo] Starts");

        return challengePartService.getChallengersDetailData(challengeId);
    }

}
