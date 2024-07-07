package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.UserCertificateDao;
import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.repository.UserCertificateRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCertificateImpl implements UserCertificateDao {

    private final UserCertificateRepository userCertificateRepository;

    @Override
    public List<UserCertificateEntity> findUserCertificateByUserId(Long id) {
        log.info("[UserCertificateImpl][findUserCertificateByID]");
        return userCertificateRepository.findByUser_UserId(id);
    }

    @Override
    public void updateUserrCertificate(UserCertificateEntity entity) {
        userCertificateRepository.save(entity);
    }

    @Override
    public void insertUserrCertificate(UserCertificateEntity entity) {
        userCertificateRepository.save(entity);
    }

    @Override
    public UserCertificateEntity findUserCertificateById(Long id) {

        return userCertificateRepository.findById(id).get();
    }

    @Override
    public Optional<UserCertificateEntity> findByUserIdCertificateId(Long userId, Long certificateId) {
        return userCertificateRepository.findByUser_UserIdAndCertificateInfo_CertificateId(userId, certificateId);
    }

}
