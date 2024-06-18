package com.web.ddajait.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.CertificateInfoEntity;

public interface CertificateInfoRepository extends JpaRepository<CertificateInfoEntity, Long>{

    Optional<CertificateInfoEntity> findByCertificateFullName(String certificateName);

}
