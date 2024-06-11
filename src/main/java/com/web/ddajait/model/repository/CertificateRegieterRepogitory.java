package com.web.ddajait.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.CertificationRegistrationEntity;

public interface CertificateRegieterRepogitory extends JpaRepository<CertificationRegistrationEntity, Long> {

    // Optional<CertificationRegistrationEntity> findByCertificateInfo_certificateId(Long certificateId);

}