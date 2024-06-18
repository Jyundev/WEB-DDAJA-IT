package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.CertificateInfoEntity;

public interface CertificateInfoDao {

    public List<CertificateInfoEntity> getAllCertificate();

    public Optional<CertificateInfoEntity> findById(Long id);

    public Optional<CertificateInfoEntity> findByCertificateName(String certificateName);

}
