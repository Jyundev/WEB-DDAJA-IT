package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.UserDto;

public interface UserService {
        // 가입 insert
        public void createMember(UserDto dto) throws Exception;

        // 조회 select
        public UserDto findById(Long id) throws Exception;

        public UserDto findByEmail(String email) throws Exception;

        // 프로필 수정 update
        public void updateUser(UserDto dto, Long id) throws Exception;

        // 회원 탈퇴 delete
        public void deleteUser(Long id) throws Exception;

        // 중복 회원 검사
        public int countMemberByMemberEmail(String email) throws Exception;

        public int countMemberByMemberNickname(String email) throws Exception;

        // 로그인 성공시 >> 로그인 유무 저장
        public void updateIsLoginByID(String Email, Boolean isLogin) throws Exception;

        // 권한 처리
        public UserDto getUserWithAuthorities(String username) throws Exception;

        public UserDto getMyUserWithAuthorities() throws Exception;

        public List<UserDto> getAllUsers() throws Exception;;

}
