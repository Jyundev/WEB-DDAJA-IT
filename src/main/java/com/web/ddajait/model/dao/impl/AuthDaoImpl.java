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

    // 서비스 클래스가 초기화될 때 권한을 데이터베이스에 삽입
    @PostConstruct
    public void initializeRoles() {
        authRepository.insertRoleUser();
        authRepository.insertRoleAdmin();
        authRepository.insertRoleChallenger();
    }
}
