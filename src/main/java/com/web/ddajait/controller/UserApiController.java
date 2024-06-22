package com.web.ddajait.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ddajait.config.handler.ResponseHandler;
import com.web.ddajait.model.dto.Response.ResponseDto;
import com.web.ddajait.model.dto.User.ProfileImageDto;
import com.web.ddajait.model.dto.User.UserCertificateDetailDto;
import com.web.ddajait.model.dto.User.UserCertificateDto;
import com.web.ddajait.model.dto.User.UserDto;
import com.web.ddajait.model.dto.User.UserPrivateInfoDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeApiDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeDto;
import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "USER API입니다.")
@Validated
public class UserApiController {

    private final UserService userService;

    // 추가 정보 수집
    @Operation(summary = "추가 정보 수집", description = "추가 정보 수집 API 입니다. 성별, 관심분야, 나이를 수집합니다. *닉네임은 프로필 수정 api를 통해 수정됩니다")
    @PostMapping("/info/{userId}")
    public ResponseEntity<ResponseDto<UserPrivateInfoDto>> getUserInfo(
            @Valid @RequestBody UserPrivateInfoDto dto, @PathVariable("userId") Long userId) throws Exception {

        log.info("[PublicController][UserPrivateInfoDto] Start - AGE: {}, Interst: {}", dto.getAge(),
                dto.getInterest());

        userService.addUserInfo(userId, dto);

        return ResponseHandler.SUCCESS(dto, "유저 정보 추가 성공!");

    }

    // 프로필 수정
    @PutMapping("/{userId}")
    @Operation(summary = "프로필 수정", description = "프로필 수정 API 입니다. 이메일을 입력 후 프로필을 수정합니다")
    public ResponseEntity<ResponseDto<UserPrivateInfoDto>> updateUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody UserPrivateInfoDto dto) throws Exception {
        userService.updateUser(dto, userId);
        return ResponseHandler.SUCCESS(dto, " 프로필 업데이트 성공");
    }

    // 프로필 이미지 수정
    @PutMapping("/profile-img/{userId}")
    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지 수정 API 입니다. 유저의 프로필 이미지만 수정합니다")
    public ResponseEntity<ResponseDto<ProfileImageDto>> updateUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody ProfileImageDto profileImage) throws Exception {
        userService.updateUserProfileImage(profileImage, userId);
        return ResponseHandler.SUCCESS(profileImage, " 프로필 이미지 업데이트 성공");
    }

    // 유저,권한 정보를 가져오는 메소드
    @GetMapping("/auth")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "권한조회", description = "권한조회 API 입니다. \"USER\" 또는 \"ADMIN\" 역할을 가진 사용자만 접근할 수 있습니다.")
    public ResponseEntity<ResponseDto<UserDto>> getMyUserInfo() throws Exception {
        log.info("[UserApiController][getMyUserInfo] Start");
        return ResponseHandler.SUCCESS(userService.getMyUserWithAuthorities(), "권한 조회");
    }

    // 탈퇴
    @Operation(summary = "유저탈퇴", description = "유저탈퇴 API 입니다. 유저 데이터가 삭제됩니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        userService.deleteUser(userId);
        return ResponseHandler.SUCCESS(null, "유저탈퇴에 성공했습니다");
    }
    /* 유저 챌린지 */

    @GetMapping("{userId}/challenges")
    @Operation(summary = "유저 챌린지 리스트 조회 API", description = "유저 챌린지 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserChallengeApiDto>>> getUserChalengeList(
            @PathVariable("userId") Long userId) throws Exception {
        log.info("[UserApiController][getUserChalengeList] Start");
        return ResponseHandler.SUCCESS(userService.getUserChallengList(userId), "유저 챌린지 리스트 조회 성공");
    }

    @Hidden
    @GetMapping("/challenge")
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

    @PutMapping("/{userId}/challenge/{challengeId}")
    @Operation(summary = "유저 챌린지 신청 및 상태 업데이트 API", description = "유저 챌린지 채린지 신청할 경우는 body 값이 없어도 되지만 상태 업데이트를 할 경우 step, day 값을 입력하세요.")
    public ResponseEntity<ResponseDto<UserChallengeDto>> updatetUserChallenge(
            @PathVariable("challengeId") Long challengeId, @PathVariable("userId") Long userId,
            @RequestBody @Valid UserChallengeDto userChallenge) throws Exception {

        // EntityUtil.copyNonNullProperties(userChallenge, dto);
        userService.updateUserChallenge(userChallenge, challengeId, userId);
        return ResponseHandler.SUCCESS(userChallenge, "유저 챌린지 상태 업데이트 성공");

    }

    /* 유저 자격증 */

    @GetMapping("/certificate/{userId}")
    @Operation(summary = "유저 자격증 리스트 조회 API", description = "유저 자격증 리스트 조회 API 입니다.")
    public ResponseEntity<ResponseDto<List<UserCertificateDetailDto>>> getUserCertificateList(
            @PathVariable("userId") Long userId)
            throws Exception {
        log.info("[UserApiController][getUserCertificateList] Start");
        return ResponseHandler.SUCCESS(userService.getUserCertificateList(userId), "유저 자격증 리스트 조회 성공");
    }

    @GetMapping("/certificate/{userId}/{certificateId}")
    @Operation(summary = "특정 유저 자격증 조회 API", description = "특정 유저 자격증 조회 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> getUserCertificate(
            @PathVariable("certificateId") Long certificateId, @PathVariable("userId") Long userId) throws Exception {
        log.info("[UserApiController][getUserCertificate] Start");

        UserCertificateDto userCertificateDto = userService.findUserCertificateId(certificateId, userId).get();

        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 조회 성공");
    }

    @PostMapping("/certificate/{userId}/{certificateId}")
    @Operation(summary = "유저 자격증 추가 API", description = "유저 자격증 추가 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> insertUserCertificate(
            @RequestBody @Valid UserCertificateDto userCertificateDto, @PathVariable("userId") Long userId,
            @PathVariable("certificateId") Long certificateId) throws Exception {
        userService.inserteUserCertificate(userCertificateDto, userId, certificateId);
        return ResponseHandler.SUCCESS(userCertificateDto, "유저 자격증 추가 성공");

    }

    @PutMapping("/certificate/{userId}/{certificateId}")
    @Operation(summary = "유저 자격증 상태 업데이트 API", description = "유저 자격증 상태 업데이트 API 입니다.")
    public ResponseEntity<ResponseDto<UserCertificateDto>> updatetUserChallenge(
            @PathVariable("certificateId") Long certificateId, @PathVariable("userId") Long userId,
            @RequestBody @Valid UserCertificateDto usercCertificateDto)
            throws Exception {

        userService.updateUserCertificate(usercCertificateDto, certificateId, userId);
        return ResponseHandler.SUCCESS(usercCertificateDto, "유저 자격증 상태 업데이트 성공");

    }

}
