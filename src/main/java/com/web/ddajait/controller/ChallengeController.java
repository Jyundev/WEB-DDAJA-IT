package com.web.ddajait.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.model.dto.ChallengeChapterDto;
import com.web.ddajait.model.dto.ChallengeInfoDto;
import com.web.ddajait.service.ChallengeChapterService;
import com.web.ddajait.service.ChallengeInfoSercive;

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
    private final ChallengeChapterService challengeChapterService;

    @Operation(summary = "모든 챌린지 데이터", description = "모든 챌린지 데이터를 가져오는 API 입니다.")
    @GetMapping("/all")
    public List<ChallengeInfoDto> getChallengeInfo() throws Exception {

        log.info("[ChallengeController][getChallengeInfo] Starts");

        return challengeInfoSercive.getAllChallengeInfo();
    }


    @Operation(summary = "모든 챌린지 챕터 데이터", description = "모든 챌린지 챕터 데이터를 가져오는 API 입니다.")
    @GetMapping("/chapter/all")
    public List<ChallengeChapterDto> getChallengeChapterInfo() throws Exception {

        log.info("[ChallengeController][getChallengeChapterInfo] Starts");

        return challengeChapterService.getAllChallengeChapterInfo();
    }

}
