package com.web.ddajait.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;

import jakarta.servlet.ServletException;

@Service
public class UserImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findById(Long userId) throws Exception {
        return userRepository.findById(userId).get();
    }

    @Override
    public UserEntity findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createMemberr(UserEntity entity) throws Exception {
        userRepository.save(entity);
    }

    @Override
    public void updateUser(UserEntity entity) throws Exception {
        userRepository.save(entity);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        userRepository.deleteById(id);

    }

    @Override
    public int countMemberByMemberEmail(String email) throws Exception {
        return userRepository.countMemberByMemberEmail(email);
    }

    @Override
    public int countMemberByMemberNickname(String nickname) throws Exception {
        return userRepository.countMemberByMemberNickname(nickname);
    }

    @Override
    public void updateIsLoginByID(UserEntity entity) throws ServletException {
        userRepository.save(entity);

    }

}
