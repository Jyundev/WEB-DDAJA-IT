package com.web.ddajait.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.ResponseDto;
import com.web.ddajait.model.dto.UserCertificateDto;
import com.web.ddajait.model.dto.UserChallengeDto;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.model.dto.UserPrivateInfoDto;
import com.web.ddajait.model.dto.ChallengePart.Challenge;
import com.web.ddajait.model.dto.UserChallenge.MemoDto;
import com.web.ddajait.service.ChallengePartService;
import com.web.ddajait.service.MemoService;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://hogwart-scholars.tistory.com/entry/Spring-Boot-SpringDoc%EA%B3%BC-Swagger%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-API-%EB%AC%B8%EC%84%9C%ED%99%94-%EC%9E%90%EB%8F%99%ED%99%94%ED%95%98%EA%B8%B0
// @Slf4jhttps://velog.io/@najiexx/Spring-Boot-3%EC%97%90-Swagger-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0springdoc-openapi

//Validated : https://mangkyu.tistory.com/174
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "UserSER API입니다.")
@Validated
public class UserApiController {

    private final UserService userService;
    private final ChallengePartService challengePartService;
    private final MemoService memoService;

    HttpSession session;

    // 추가 정보 수집
    @Operation(summary = "추가 정보 수집", description = "추가 정보 수집 API 입니다. 성별, 관심분야, 나이를 수집합니다.")
    @PostMapping("/info/{userId}")
    public ResponseEntity<ResponseDto<UserPrivateInfoDto>> getUserInfo(
            @Valid @RequestBody UserPrivateInfoDto dto, @PathVariable Long userId) throws Exception {

        log.info("[PublicController][UserPrivateInfoDto] Start - Gender: {}, Interst: {}", dto.getGender(),
                dto.getInterest());

        // Long user_id = CommonUtils.checkSessionId(session);

        log.info("[PublicController][UserPrivateInfoDto] Start - user_id", userId);

        userService.addUserInfo(userId, dto);

        return ResponseHandler.SUCCESS(dto, "회원가입");

    }

    // 프로필 수정
    @PutMapping("/{userId}")
    @Operation(summary = "프로필 수정", description = "프로필 수정 API 입니다. 이메일을 입력 후 프로필을 수정합니다")
    public ResponseEntity<ResponseDto<UserPrivateInfoDto>> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserPrivateInfoDto dto) throws Exception {
        userService.updateUser(dto, userId);
        return ResponseHandler.SUCCESS(dto, " 프로필 업데이트 성공");
    }

    // 유저,권한 정보를 가져오는 메소드
    @GetMapping("/Auth")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "권한조회", description = "권한조회 API 입니다. \"USER\" 또는 \"ADMIN\" 역할을 가진 사용자만 접근할 수 있습니다.")
    public ResponseEntity<ResponseDto<UserDto>> getMyUserInfo() throws Exception {
        log.info("[UserApiController][getMyUserInfo] Start");
        return ResponseHandler.SUCCESS(userService.getMyUserWithAuthorities(), "권한 조회");
    }

    /* 유저 챌린지 */

    @GetMapping("/challenge")
    @Operation(summary = "유저 챌린지 리스트 조회 API", description = "유저 챌린지 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserChallengeDto>>> getUserChalengeList() throws Exception {
        log.info("[UserApiController][getUserChalengeList] Start");
        return ResponseHandler.SUCCESS(userService.getUserChallengList(), "유저 챌린지 리스트 조회 성공");
    }

    @GetMapping("/challenge/specific")
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

    @PostMapping("/challenge/update/{challengeId}/{userId}")
    @Operation(summary = "유저 챌린지 신청 및 상태 업데이트 API", description = "유저 챌린지 채린지 신청할 경우는 body 값이 없어도 되지만 상태 업데이트를 할 경우 step, day 값을 입력하세요.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> updatetUserChallenge(
            @PathVariable Long challengeId, @PathVariable Long userId,
            @RequestBody @Valid UserChallengeDto userChallenge) throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserChallenge(userChallenge, challengeId, userId);
        return ResponseHandler.SUCCESS(userChallenge, "유저 챌린지 상태 업데이트 성공");

    }

    @Operation(summary = "챌린지 상세 페이지 데이터", description = "챌린지 상세 페이지 데이터를 가져오는 API 입니다. \n*현제 데이터 오류로 challengeId = 7, 8, 9 테스트 가능")
    @GetMapping("/challengePage/{challengeId}/{userId}")
    public Challenge getchallengeDetailPageInfo(@PathVariable("challengeId") Long challengeId,
            @PathVariable("userId") Long userId) throws Exception {

        log.info("[ChallengeController][getchallengeDetailPageInfo] Starts");

        return challengePartService.getChallengersDetailData(challengeId, userId);
    }

    // @Operation(summary = "챌린지 데이별 메모 저장", description = "챌린지 데이별 메모를 저장하는 API. ChallengeId, UserId, Step, Day 값이 필요합니다")
    // @PostMapping("/challengeMemo/{challengeId}/{userId}")
    // public ResponseEntity<ResponseDto<MemoDto>> saveChallengeMemo(@PathVariable("challengeId") Long challengeId,
    //         @PathVariable("userId") Long userId,
    //         @Valid @RequestBody MemoDto dto) throws Exception {
    //     log.info("[ChallengeController][getchallengeDetailPageInfo] Starts");
    //     memoService.saveUserChallengeMemo(dto, userId, challengeId);
    //     return ResponseHandler.SUCCESS(dto, "챌린지 데이별 메모 저장 성공");
    // }

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

    /* 유저 자격증 */

    @GetMapping("/certificate/{userId}")
    @Operation(summary = "유저 자격증 리스트 조회 API", description = "유저 자격증 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserCertificateDto>>> getUserCertificateList(@PathVariable Long userId)
            throws Exception {
        log.info("[UserApiController][getUserCertificateList] Start");
        return ResponseHandler.SUCCESS(userService.getUserCertificateList(userId), "유저 자격증 리스트 조회 성공");
    }

    @GetMapping("/certificate/specific/{userId}/{certificateId}")
    @Parameter(description = "challegeId", example = "1")
    @Operation(summary = "특정 유저 자격증 조회 API", description = "특정 유저 자격증 조회 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> getUserCertificate(
            @PathVariable Long certificateId, @PathVariable Long userId) throws Exception {
        log.info("[UserApiController][getUserCertificate] Start");

        if (userId == null) {
            throw new Exception("User is not logged in");
        }
        UserCertificateDto userCertificateDto = userService.findUserCertificateId(certificateId, userId);

        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 조회 성공");
    }

    @PostMapping("/certificate/insert/{userId}/{certificateId}")
    @Operation(summary = "유저 자격증 추가 API", description = "유저 자격증 추가 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> insertUserCertificate(
            @RequestBody @Valid UserCertificateDto userCertificateDto, @PathVariable("userId") Long userId,
            @PathVariable("certificateId") Long certificateId) throws Exception {
        userService.inserteUserCertificate(userCertificateDto, userId, certificateId);
        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 추가 성공");

    }

    @PostMapping("/certificate/update/{certificateId}/{userId}")
    @Operation(summary = "유저 자격증 상태 업데이트 API", description = "유저 자격증 상태 업데이트 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> updatetUserChallenge(
            @PathVariable Long certificateId, @PathVariable Long userId,
            @RequestBody @Valid UserCertificateDto usercCertificateDto)
            throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserCertificate(usercCertificateDto, certificateId, userId);
        return ResponseHandler.SUCCESS(usercCertificateDto, "유저 자격증 상태 업데이트 성공");

    }

}
