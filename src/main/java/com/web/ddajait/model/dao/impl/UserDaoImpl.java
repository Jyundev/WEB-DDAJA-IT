package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ddajait.config.error.custom.NotFoundMemberException;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;
import com.web.ddajait.util.SecurityUtil;

import jakarta.servlet.ServletException;

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long userId) throws Exception {
        return userRepository.findById(userId);
    }

    @Override
    public UserEntity findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createMember(UserEntity entity) throws Exception {
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

    @Override
    public UserEntity getMyUserWithAuthorities() throws Exception {
        UserEntity userEntity = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByEmail)
                .orElseThrow(() -> new NotFoundMemberException("Member not found"));
        return userEntity;
    }

    @Override
    public UserEntity getUserWithAuthorities(String email) throws Exception {
        return userRepository.findOneWithAuthoritiesByEmail(email).orElse(null);
    }


}
