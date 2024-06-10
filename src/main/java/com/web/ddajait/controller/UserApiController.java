package com.web.ddajait.controller;

import java.util.List;

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
import com.web.ddajait.service.UserService;
import com.web.ddajait.util.CommonUtils;

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

    UserService userService;

    HttpSession session;

    // 추가 정보 수집
    @Operation(summary = "추가 정보 수집", description = "추가 정보 수집 API 입니다. 성별, 관심분야, 나이를 수집합니다.")
    @PostMapping("/info")
    public ResponseEntity<ResponseDto<UserPrivateInfoDto>> getUserInfo(
            @Valid @RequestBody UserPrivateInfoDto dto) throws Exception {

        log.info("[PublicController][UserPrivateInfoDto] Start - Gender: {}, Interst: {}", dto.getGender(),
                dto.getInterest());

        Long user_id = CommonUtils.checkSessionId(session);
        
        log.info("[PublicController][UserPrivateInfoDto] Start - user_id", user_id);



        userService.addUserInfo(user_id, dto);

        return ResponseHandler.SUCCESS(dto, "회원가입");

    }

    // 프로필 수정
    @PutMapping("/{email}")
    @Operation(summary = "프로필 수정", description = "프로필 수정 API 입니다. 이메일을 입력 후 프로필을 수정합니다")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(
            @Parameter(description = "유저 Email", example = "Jyundev@gmail.com") @PathVariable String email,
            @Valid @RequestBody UserDto dto) throws Exception {
        userService.updateUser(dto, email);
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
            @RequestParam Long challegeId) throws Exception {
        log.info("[UserApiController][getUserChalenge] Start");

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new Exception("User is not logged in");
        }
        UserChallengeDto userChallengeDto = userService.findByUserIdChallengeId(challegeId, userId);

        return ResponseHandler.SUCCESS(userChallengeDto, "유저 챌린지 조회 성공");
    }

    @PostMapping("/challenge/insert")
    @Operation(summary = "유저 챌린지 신청 API", description = "유저 챌린지 신청 API 입니다.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> insertUserChallenge(
            @RequestBody @Valid UserChallengeDto userChallenge) throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.insertUserChallenge(userChallenge);
        return ResponseHandler.SUCCESS(userChallenge, "유저 챌린지 추가 성공");

    }

    @PostMapping("/challenge/update/{challengeId}")
    @Operation(summary = "유저 챌린지 상태 업데이트 API", description = "유저 챌린지 업데이트 API 입니다.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> updatetUserChallenge(
            @PathVariable Long challengeId, @RequestBody @Valid UserChallengeDto userChallenge) throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserChallenge(userChallenge, challengeId);
        return ResponseHandler.SUCCESS(userChallenge, "유저 챌린지 상태 업데이트 성공");

    }
    /* 유저 자격증 */

    @GetMapping("/certificate")
    @Operation(summary = "유저 자격증 리스트 조회 API", description = "유저 자격증 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserCertificateDto>>> getUserCertificateList() throws Exception {
        log.info("[UserApiController][getUserCertificateList] Start");
        return ResponseHandler.SUCCESS(userService.getUserCertificateList(), "유저 자격증 리스트 조회 성공");
    }

    @GetMapping("/certificate/specific")
    @Parameter(description = "challegeId", example = "1")
    @Operation(summary = "특정 유저 자격증 조회 API", description = "특정 유저 자격증 조회 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> getUserCertificate(
            @RequestParam Long certificateId) throws Exception {
        log.info("[UserApiController][getUserCertificate] Start");

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new Exception("User is not logged in");
        }
        UserCertificateDto userCertificateDto = userService.findUserCertificateId(certificateId, userId);

        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 조회 성공");
    }

    @PostMapping("/certificate/insert")
    @Operation(summary = "유저 자격증 추가 API", description = "유저 자격증 추가 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> insertUserCertificate(
            @RequestBody @Valid UserCertificateDto userCertificateDto) throws Exception {
        userService.inserteUserCertificate(userCertificateDto);
        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 추가 성공");

    }

    @PostMapping("/certificate/update/{certificateId}")
    @Operation(summary = "유저 자격증 상태 업데이트 API", description = "유저 자격증 상태 업데이트 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> updatetUserChallenge(
            @PathVariable Long certificateId, @RequestBody @Valid UserCertificateDto usercCertificateDto)
            throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserCertificate(usercCertificateDto, certificateId);
        return ResponseHandler.SUCCESS(usercCertificateDto, "유저 자격증 상태 업데이트 성공");

    }

}
