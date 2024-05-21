package com.web.ddajait.model.dao;

import com.web.ddajait.model.entity.UserEntity;

public interface UserDao {

    // select
    public UserEntity findById(Long userId);

    public UserEntity findByEmail(String email);

    // join
    public void createMemberr(UserEntity entity);

    // update -> profile edit
    public void updateUser(UserEntity entity);

    // delete
    public void deleteUser(Long id);

    // 중복 회원 검사 
    public int countMemberByMemberEmail(String email) throws Exception;

    public int countMemberByMemberNickname(String email) throws Exception;

    // // login 성공 확인
    // public void updateIsLoginByEmail(UserEntity entity) throws ServletException;

}
