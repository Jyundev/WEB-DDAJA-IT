package com.web.ddajait.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;

@Service
public class UserImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).get();

    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void joinUser(UserEntity entity) {
        userRepository.save(entity);
    }

    @Override
    public void updateUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        
    }

}
