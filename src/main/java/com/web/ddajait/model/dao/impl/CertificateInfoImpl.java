package com.web.ddajait.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.CertificateInfoDao;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.repository.CertificateInfoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CertificateInfoImpl implements CertificateInfoDao{

    final private CertificateInfoRepository certificateInfoRepository;
    @Override
    public List<CertificateInfoEntity> getAllCertificate() {
        log.info("[CertificateInfoImpl][getAllCertificate] Starts");
        
        return certificateInfoRepository.findAll();
    }

}
