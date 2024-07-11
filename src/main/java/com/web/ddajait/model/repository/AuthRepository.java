package com.web.ddajait.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.ddajait.model.entity.AuthorityEntity;

// authority 테이블에 ROLE_USER, ROLE_ADMIN, ROLE_CHALLENGER 권한을 삽입
public interface AuthRepository extends JpaRepository<AuthorityEntity, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO authority (authority_name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE authority_name=authority_name", nativeQuery = true)
    void insertRoleUser();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE authority_name=authority_name", nativeQuery = true)
    void insertRoleAdmin();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO authority (authority_name) VALUES ('ROLE_CHALLENGER') ON DUPLICATE KEY UPDATE authority_name=authority_name", nativeQuery = true)
    void insertRoleChallenger();
}
