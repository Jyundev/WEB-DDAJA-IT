package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.PartQuestionDao;
import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.service.PartQuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class PartQuestionServiceImpl implements PartQuestionService {

    private final PartQuestionDao partQuestionDao;

    @Override
    public List<PartQuestionDto> getQuestionListByPartId(Long partId) throws Exception {
        log.info("[PartQuestionServiceImpl][getQuestionListByPartId] Starts");
        return partQuestionDao.findByPartId(partId).stream()
                .map(PartQuestionDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartQuestionDto> getQuestionListbyCertificatePartID(Long certificatePartId) throws Exception {
        log.info("[PartQuestionServiceImpl][getQuestionListbyCertificatePartID] Starts");
        
        return partQuestionDao.findByCetificatePartId(certificatePartId).stream()
                .map(PartQuestionDto::from)
                .collect(Collectors.toList());

    }

}
