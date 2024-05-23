package com.web.ddajait.model.dao;

import com.web.ddajait.model.entity.UserEntity;

import jakarta.servlet.ServletException;

public interface UserDao {

    /**
     * user 테이블에서 특정 유저의 정보를 가져온다.
     * 
     * @return 특정 유저의 정보
     */

    public UserEntity findById(Long userId) throws Exception;;

    public UserEntity findByEmail(String email) throws Exception;;

    /**
     * 유저를 생성한다.
     */

    public void createMemberr(UserEntity entity) throws Exception;;

    /**
     * 유저 프로필을 수정한다.
     */
    public void updateUser(UserEntity entity) throws Exception;;

    /**
     * 유저를 삭제한다.
     */
    public void deleteUser(Long id) throws Exception;;

    /**
     * 특정 조건에 맞는 유저으 수를 구한다. .
     */
    public int countMemberByMemberEmail(String email) throws Exception;

    public int countMemberByMemberNickname(String email) throws Exception;

    /**
     * 로그인 상태를 확인한다.
     */
    public void updateIsLoginByID(UserEntity entity) throws ServletException;

}
