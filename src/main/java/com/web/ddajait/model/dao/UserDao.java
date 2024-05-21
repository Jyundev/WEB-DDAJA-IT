package com.web.ddajait.model.dao;

import com.web.ddajait.model.entity.UserEntity;

public interface UserDao {

    // select
    public UserEntity findById(Long userId);

    public UserEntity findByEmail(String email);

    // join
    public void joinUser(UserEntity entity);

    // update -> profile edit
    public void updateUser(UserEntity entity);

    // delete
    public void deleteUser(Long id);

    // // login 성공 확인
    // public void updateIsLoginByEmail(UserEntity entity) throws ServletException;

}
