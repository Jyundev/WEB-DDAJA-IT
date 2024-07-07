package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.CertificateInfoDao;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.repository.CertificateInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateInfoImpl implements CertificateInfoDao {

    private final CertificateInfoRepository certificateInfoRepository;

    @Override
    public List<CertificateInfoEntity> getAllCertificate() {
        log.info("[CertificateInfoImpl][getAllCertificate] Starts");
        return certificateInfoRepository.findAll();
    }

    @Override
    public Optional<CertificateInfoEntity> findById(Long id) {
        return certificateInfoRepository.findById(id);
    }

    @Override
    public Optional<CertificateInfoEntity> findByCertificateName(String certificateName) {
        return certificateInfoRepository.findByCertificateFullName(certificateName);
    }

}
