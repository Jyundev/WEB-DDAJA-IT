package com.web.ddajait.model.dao;

import com.web.ddajait.model.entity.UserEntity;

public interface UserDao {

    // select
    public UserEntity findById(Long userId) throws Exception;;

    public UserEntity findByEmail(String email) throws Exception;;

    // join
    public void createMemberr(UserEntity entity) throws Exception;;

    // update -> profile edit
    public void updateUser(UserEntity entity) throws Exception;;

    // delete
    public void deleteUser(Long id) throws Exception;;

    // 중복 회원 검사 
    public int countMemberByMemberEmail(String email) throws Exception;

    public int countMemberByMemberNickname(String email) throws Exception;

    // // login 성공 확인
    // public void updateIsLoginByEmail(UserEntity entity) throws ServletException;

}
