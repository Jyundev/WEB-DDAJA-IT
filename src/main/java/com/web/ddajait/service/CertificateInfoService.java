package com.web.ddajait.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;
import com.web.ddajait.model.dto.CertificateInfo.ExamStandard.JsonWrapper;

import io.jsonwebtoken.io.IOException;

public interface CertificateInfoService {

    public List<CertificateInfoDto> getAllCertificate();

    public CertificateInfoDto findById(Long id);

    public ExamList getExamContent(Long id) throws  IOException, JsonMappingException, JsonProcessingException;
   
    public JsonWrapper  getExamStandard(Long id) throws  JsonMappingException, JsonProcessingException;

}
