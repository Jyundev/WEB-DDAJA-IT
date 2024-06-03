package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;

public interface CertificateInfoService {

    public List<CertificateInfoDto> getAllCertificate();
    
    public CertificateInfoDto findById(Long id);
}
