package com.web.ddajait.model.dao;

import java.util.List;

import com.web.ddajait.model.entity.CertificationRegistrationEntity;


public interface CertificationRegistrationDao {
    
    public List<CertificationRegistrationEntity> getAllCerticationResgitration();

    // public Optional<CertificationRegistrationEntity> findByCertificateId(Long certificateId);
}
