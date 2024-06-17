package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.User.UserCertificateDto;
import com.web.ddajait.model.dto.User.UserDto;
import com.web.ddajait.model.dto.User.UserPrivateInfoDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeApiDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeDto;

import jakarta.servlet.ServletException;

public interface UserService {
        /* 기본 기능 */

        // 가입 insert
        public void createMember(UserDto dto) throws Exception;

        // 조회 select
        public UserDto findById(Long id) throws Exception;

        public UserDto findByEmail(String email) throws Exception;

        // 추가 정보 수집
        public void addUserInfo(Long userId, UserPrivateInfoDto dto) throws Exception;

        // 프로필 수정 update
        public void updateUser(UserPrivateInfoDto dto, Long userId) throws Exception;

        // 회원 탈퇴 delete
        public void deleteUser(Long id) throws Exception;

        // 중복 회원 검사
        public int countMemberByMemberEmail(String email) throws Exception;

        public int countMemberByMemberNickname(String email) throws Exception;

        // 로그인 성공시 >> 로그인 유무 저장
        public void updateIsLoginByID(String Email, Boolean isLogin) throws Exception, ServletException;

        // 권한 처리
        public UserDto getUserWithAuthorities(String username) throws Exception;

        public UserDto getMyUserWithAuthorities() throws Exception;

        public List<UserDto> getAllUsers() throws Exception;

        // 유저 id (pk) 반환
        public Long getUserId(String email) throws Exception;

        /* 자격증 */

        // 유저 자격증 정보 조회
        public List<UserCertificateDto> getUserCertificateList(Long userId) throws Exception;

        // 자격증 정보 조회
        public UserCertificateDto getUserCertificate(Long userCertificateId) throws Exception;

        // 자격증 ID 조회
        public UserCertificateDto findUserCertificateId(Long userCertificateId, Long userId) throws Exception;

        // 자격증 정보 업데이트
        public void updateUserCertificate(UserCertificateDto dto, Long certificateId, Long userId) throws Exception;

        // 자격증 정보 추가
        public void inserteUserCertificate(UserCertificateDto dto, Long userId, Long certificateId) throws Exception;

        /* 챌린지 */

        // 유저 챌린지 정보 조회
        public List<UserChallengeApiDto> getUserChallengList(Long userId) throws Exception;

        // 챌린지 정보 조회
        public UserChallengeDto getUserChalleng(Long userChallengeId) throws Exception;

        // 챌린지 ID 조회
        public UserChallengeDto findByUserIdChallengeId(Long challengeId, Long userId) throws Exception;

        // 챌린지 신청 및 상태 업데이트
        public void updateUserChallenge(UserChallengeDto dto, Long challengeID, Long UserId) throws Exception;

        // 챌린지 정보 추가
        public void insertUserChallenge(UserChallengeDto dto, Long userId, Long challengeId) throws Exception;

        // 챌린지별 참여 유저 수
        public int countMemberByChallengeId(Long challengeId);

}
