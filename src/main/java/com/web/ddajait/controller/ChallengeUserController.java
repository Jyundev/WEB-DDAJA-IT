package com.web.ddajait.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.UserWrongQuestionDto;
import com.web.ddajait.model.dto.ChallengePart.Challenge;
import com.web.ddajait.model.dto.UserChallenge.MemoDto;
import com.web.ddajait.model.dto.UserChallenge.UserChallengeApiDto;
import com.web.ddajait.model.dto.UserChallenge.UserChallengeDto;
import com.web.ddajait.service.ChallengePartService;
import com.web.ddajait.service.MemoService;
import com.web.ddajait.service.PartQuestionService;
import com.web.ddajait.service.UserService;
import com.web.ddajait.service.UserWrongQuestionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ChallengeUserController {

    private final UserService userService;
    private final ChallengePartService challengePartService;
    private final MemoService memoService;
    private final PartQuestionService partQuestionService;
    private final UserWrongQuestionService userWrongQuestionService;

    @GetMapping("/{userId}")
    @Operation(summary = "유저 챌린지 리스트 조회 API", description = "유저 챌린지 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserChallengeApiDto>>> getUserChalengeList(
            @PathVariable("userId") Long userId) throws Exception {
        log.info("[UserApiController][getUserChalengeList] Start");
        return ResponseHandler.SUCCESS(userService.getUserChallengList(userId), "유저 챌린지 리스트 조회 성공");
    }

    @GetMapping("/specific")
    @Operation(summary = "특정 챌린지 상테 조회 API", description = "로그인 유저의 특정 챌린지 상태 조회 API 입니다.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> getUserChalenge(
            @RequestParam Long challegeId, @RequestParam Long userId) throws Exception {
        log.info("[UserApiController][getUserChalenge] Start");

        if (userId == null) {
            throw new Exception("User is not logged in");
        }
        UserChallengeDto userChallengeDto = userService.findByUserIdChallengeId(challegeId, userId);

        return ResponseHandler.SUCCESS(userChallengeDto, "유저 챌린지 조회 성공");
    }

    @PostMapping("/update/{challengeId}/{userId}")
    @Operation(summary = "유저 챌린지 신청 및 상태 업데이트 API", description = "유저 챌린지 채린지 신청할 경우는 body 값이 없어도 되지만 상태 업데이트를 할 경우 step, day 값을 입력하세요.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> updatetUserChallenge(
            @PathVariable Long challengeId, @PathVariable Long userId,
            @RequestBody @Valid UserChallengeDto userChallenge) throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserChallenge(userChallenge, challengeId, userId);
        return ResponseHandler.SUCCESS(userChallenge, "유저 챌린지 상태 업데이트 성공");

    }

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
    public ResponseEntity<ResponseDto<UserWrongQuestionDto>> getUserWrongQuestionById(
            @PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId)
            throws Exception {
        log.info("[ChallengeController][getAllUserWrongQuestionById] Starts");
        UserWrongQuestionDto userWrongQuestionDtos = userWrongQuestionService.findWrongQuestionByUserIdChallengeId(
                userId,
                challengeId);
        return ResponseHandler.SUCCESS(userWrongQuestionDtos, "챌린지별 유저 오답문제를 가져오기 성공");
    }

    @Operation(summary = "챌린지별 유저 오답문제 저장 및 수정", description = "챌린지별 유저 오답문제 저장 및 수정이 이루어집니다")
    @PutMapping("/challengePage/wrongQuestion/{userId}/{challengeId}")
    public ResponseEntity<ResponseDto<UserWrongQuestionDto>> modifyUserWrongQuestionByUserIdAndChalleneId(
            @PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId,
            @RequestBody UserWrongQuestionDto uDto)
            throws Exception {
        log.info("[ChallengeController][modifyUserWrongQuestionByUserIdAndChalleneId] Starts");
        userWrongQuestionService.modifyWrongQuestion(
                userId,
                challengeId, uDto);
        return ResponseHandler.SUCCESS(uDto, "챌린지별 유저 오답문제를 저장하기 성공");
    }

}
