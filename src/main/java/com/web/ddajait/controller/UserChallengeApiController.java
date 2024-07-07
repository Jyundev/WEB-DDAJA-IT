package com.web.ddajait.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ChallengePart.Challenge;
import com.web.ddajait.model.dto.ChallengePart.PartQuestionDto;
import com.web.ddajait.model.dto.Response.ResponseDto;
import com.web.ddajait.model.dto.User.UserWrongQuestionDto;
import com.web.ddajait.model.dto.User.UserChallenge.MemoDto;
import com.web.ddajait.service.ChallengePartService;
import com.web.ddajait.service.MemoService;
import com.web.ddajait.service.PartQuestionService;
import com.web.ddajait.service.UserWrongQuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Validated : https://mangkyu.tistory.com/174
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user/challenge")
@Tag(name = "Challenger", description = "Challenger API입니다.")
@Validated
public class UserChallengeApiController {

    private final ChallengePartService challengePartService;
    private final MemoService memoService;
    private final PartQuestionService partQuestionService;
    private final UserWrongQuestionService userWrongQuestionService;

    @Operation(summary = "챌린지 상세 페이지 데이터", description = "챌린지 상세 페이지 데이터를 가져오는 API 입니다. \n*현제 데이터 오류로 challengeId = 1, 50 테스트 가능")
    @GetMapping("/challengePage/{challengeId}/{userId}")
    public Challenge getchallengeDetailPageInfo(
            @PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId) throws Exception {

        log.info("[ChallengeController][getchallengeDetailPageInfo] Starts");

        return challengePartService.getChallengersDetailData(userId, challengeId);
    }

    @Operation(summary = "챌린지 데이별 메모 업데이트", description = "챌린지 데이별 메모를 수정하는 API")
    @PostMapping("/challengeMemo/{challengeId}/{userId}")
    public ResponseEntity<ResponseDto<MemoDto>> modifyChallengeMemo(@PathVariable("challengeId") Long challengeId,
            @PathVariable("userId") Long userId,
            @Valid @RequestBody MemoDto dto) throws Exception {
        log.info("[ChallengeController][modifyChallengeMemo] Starts");
        memoService.modifyUserChallengeMemo(userId, challengeId, dto);
        return ResponseHandler.SUCCESS(dto, "챌린지 데이별 메모 수정 성공");
    }

    @Operation(summary = "챌린지 데이별 메모 가져오기", description = "챌린지 데이별 메모를 가져오는 API. ChallengeId, UserId, Step, Day 값이 필요합니다")
    @GetMapping("/challengeMemo/{challengeId}/{userId}")
    public ResponseEntity<ResponseDto<MemoDto>> getChallengeMemo(@Param("challengeId") Long challengeId,
            @Param("userId") Long userId, @Param("step") int step, @Param("day") int day) throws Exception {
        log.info("[ChallengeController][getChallengeMemo] Starts");
        MemoDto memoDto = memoService.findMemo(userId, challengeId, step, day);
        return ResponseHandler.SUCCESS(memoDto, "챌린지 데이별 메모 저장 성공");
    }

    @Operation(summary = "챌린지별 모든 기출문제 가져오기", description = "챌린지별 모든 파트의 기출문제를 제공합니다")
    @GetMapping("/challengePage/allQuestion/{certificateId}")
    public ResponseEntity<ResponseDto<List<PartQuestionDto>>> getAllPartQuestionByChallengeId(
            @PathVariable("certificateId") Long certificateId)
            throws Exception {
        log.info("[ChallengeController][getAllPartQuestionByChallengeId] Starts");
        List<PartQuestionDto> partQuestionDtos = partQuestionService.getQuestionListbyCertificateId(certificateId);
        return ResponseHandler.SUCCESS(partQuestionDtos, "챌린지별 모든 기출문제 가져오기 성공");
    }

    @Operation(summary = "챌린지별 유저 오답문제 가져오기", description = "챌린지별 유저 오답문제를 제공합니다")
    @GetMapping("/challengePage/wrongQuestion/{userId}/{challengeId}")
    public ResponseEntity<ResponseDto<List<Integer>>> getUserWrongQuestionById(
            @PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId)
            throws Exception {
        log.info("[ChallengeController][getAllUserWrongQuestionById] Starts");
        List<UserWrongQuestionDto> userWrongQuestionDtos = userWrongQuestionService
                .findWrongQuestionByUserIdChallengeId(userId, challengeId);

        List<Integer> questions = userWrongQuestionDtos.stream()
                .flatMap(dto -> dto.getWrongQuestions().stream())
                .collect(Collectors.toList());

        return ResponseHandler.SUCCESS(questions, "챌린지별 유저 오답문제를 저장하기 성공");

    }

    @Operation(summary = "챌린지별 유저 오답문제 저장 및 수정", description = "챌린지별 유저 오답문제 저장 및 수정이 이루어집니다")
    @PutMapping("/challengePage/wrongQuestion/{userId}/{challengeId}/{step}")
    public ResponseEntity<ResponseDto<UserWrongQuestionDto>> modifyUserWrongQuestionByUserIdAndChalleneId(
            @PathVariable("userId") Long userId,
            @PathVariable("challengeId") Long challengeId,
            @PathVariable("step") int step,
            @RequestBody UserWrongQuestionDto uDto)
            throws Exception {
        log.info("[ChallengeController][modifyUserWrongQuestionByUserIdAndChalleneId] Starts");
        userWrongQuestionService.modifyWrongQuestion(
                userId,
                challengeId, step, uDto);
        return ResponseHandler.SUCCESS(uDto, "챌린지별 유저 오답문제를 저장하기 성공");
    }
}
