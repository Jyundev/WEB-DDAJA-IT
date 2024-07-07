package com.web.ddajait.model.dao.impl;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.AuthDao;
import com.web.ddajait.model.repository.AuthRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {

    private final AuthRepository authRepository;

    @PostConstruct
    public void initializeRoles() {
        authRepository.insertRoleUser();
        authRepository.insertRoleAdmin();
    }
}
