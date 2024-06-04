package com.web.ddajait.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.UserCertificateDao;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserCertificateImpl implements UserCertificateDao {

    private final UserCertificateRepository userCertificateRepository;

    @Override
    public List<UserCertificateEntity> findUserCertificateByUserId(Long id) {
        log.info("[UserCertificateImpl][findUserCertificateByID]");
        return userCertificateRepository.findByUser_User_id(id);
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
    public UserCertificateEntity findUserCertificateId(CertificateInfoEntity certificateInfoEntity,
            UserEntity userEntity) {
        return userCertificateRepository.findByUserAndCertificateInfo(userEntity, certificateInfoEntity).orElse(null);
    }

}
