package com.web.ddajait.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.ddajait.model.dao.CertificateInfoDao;
import com.web.ddajait.model.dto.CertificateInfo.CertificateInfoDto;
import com.web.ddajait.model.dto.CertificateInfo.ExamContent.ExamList;
import com.web.ddajait.model.dto.CertificateInfo.ExamStandard.JsonWrapper;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.service.CertificateInfoService;

import io.jsonwebtoken.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CertificateInfoServiceImpl implements CertificateInfoService {

    private final CertificateInfoDao certificateInfoDao;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @Override
    public ExamList getExamContent(Long id) throws IOException, JsonMappingException, JsonProcessingException {
        if (certificateInfoDao.findById(id).isPresent()) {
            CertificateInfoEntity entity = certificateInfoDao.findById(id).get();

            ExamList examList = objectMapper.readValue(entity.getExamContent(), ExamList.class);
            return examList;
        } else {
            throw new IllegalArgumentException("CertificateInfoEntity not found for id: " + id);

        }

    }

    @Override
    public JsonWrapper getExamStandard(Long id) throws JsonMappingException, JsonProcessingException {
        if (certificateInfoDao.findById(id).isPresent()) {
            CertificateInfoEntity entity = certificateInfoDao.findById(id).get();
            JsonNode rootNode = objectMapper.readTree(entity.getExamContent());
            JsonWrapper jsonWrapper = new JsonWrapper();
            log.info("[CertificateInfoServiceImpl][getExamStandard] Starts");

            if (rootNode.has("subject")) {
                JsonNode subjectNode = rootNode.get("subject");
            log.info("[CertificateInfoServiceImpl][getExamStandard] subjectNode : "+subjectNode);

                if (subjectNode.isArray()) {
                    // subject 필드가 배열인 경우
                    jsonWrapper = objectMapper.treeToValue(rootNode, JsonWrapper.class);
                } else if (subjectNode.isTextual()) {
                    // subject 필드가 텍스트인 경우
                    jsonWrapper.setSimpleSubject(subjectNode.asText());
                }
            } else if (rootNode.has("link")) {
                jsonWrapper = objectMapper.treeToValue(rootNode, JsonWrapper.class);
            } else {
                throw new IllegalArgumentException("Invalid JSON structure for id: " + id);
            }

            return jsonWrapper;
        } else {
            throw new IllegalArgumentException("CertificateInfoEntity not found for id: " + id);
        }

    }

}
