package com.web.ddajait.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.AuthDao;
import com.web.ddajait.model.repository.AuthRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthDaoImpl implements AuthDao{
    
    @Autowired
    private AuthRepository authRepository;

    @PostConstruct
    public void initializeRoles() {
        authRepository.insertRoleUser();
        authRepository.insertRoleAdmin();
    }
}
