package com.web.ddajait.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.CertificateInfoDao;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.service.CertificateInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CertificateInfoServiceImpl implements CertificateInfoService {

    private final CertificateInfoDao certificateInfoDao;

    @Override
    public List<CertificateInfoDto> getAllCertificate() {
        log.info("[CertificateInfoServiceImpl][getAllCertificate] Starts");

        return certificateInfoDao.getAllCertificate().stream()
                .map(CertificateInfoDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateInfoDto findById(Long id) {
        Optional<CertificateInfoEntity> certificateInfoEntityOptional = certificateInfoDao.findById(id);
        if (certificateInfoEntityOptional.isPresent()) {
            CertificateInfoEntity certificateInfoEntity = certificateInfoEntityOptional.get();
            return CertificateInfoDto.from(certificateInfoEntity);
        } else {
            return null;
        }
    }

}
