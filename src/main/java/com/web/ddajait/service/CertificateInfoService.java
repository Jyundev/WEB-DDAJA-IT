package com.web.ddajait.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityJsonWrapper;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;
import com.web.ddajait.model.dto.CertificateInfo.ExamStandard.ExamStandardJsonWrapper;

import io.jsonwebtoken.io.IOException;

public interface CertificateInfoService {

    public List<CertificateInfoDto> getAllCertificate();

    public CertificateInfoDto findById(Long id);

    public ExamList getExamContent(Long id) throws  IOException, JsonMappingException, JsonProcessingException;
   
    public ExamStandardJsonWrapper  getExamStandard(Long id) throws  JsonMappingException, JsonProcessingException;

    public ElibilityJsonWrapper  getElibility(Long id) throws  JsonMappingException, JsonProcessingException;

}
