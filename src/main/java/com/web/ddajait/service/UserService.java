package com.web.ddajait.service;

import com.web.ddajait.model.dto.UserDto;

public interface UserService {
        // 가입 insert
        public void createMemberr(UserDto dto) throws Exception;

        // 조회 select
        public UserDto findById(Long id) throws Exception;
    
        public UserDto findByEmail(String email) throws Exception;
    
        // 프로필 수정 update
        public void updateUser(UserDto dto) throws Exception;
    
        // 회원 탈퇴 delete
        public void deleteUser(Long id) throws Exception;

        // 중복 회원 검사 
        public int countMemberByMemberEmail(String email) throws Exception;
        public int countMemberByMemberNickname(String email) throws Exception;

        // 로그인 성공시 >> 로그인 유무 저장
        public void updateIsLoginByID(String Email, Boolean isLogin) throws Exception;
    
        // Authority 정보 가져오기 
        public int getMyUserWithAuthorities(final String username) throws Exception;
        public int getUserWithAuthorities(final String username) throws Exception;

}
