package com.web.ddajait.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.PartQuestionDao;
import com.web.ddajait.model.dto.ChallengePart.PartQuestionDto;
import com.web.ddajait.service.PartQuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartQuestionServiceImpl implements PartQuestionService {

    private final PartQuestionDao partQuestionDao;


    @Override
    public List<PartQuestionDto> getQuestionListbyCertificatePartID(Long certificatePartId) throws Exception {
        log.info("[PartQuestionServiceImpl][getQuestionListbyCertificatePartID] Starts");

        return partQuestionDao.findByCetificatePartId(certificatePartId).stream()
                .map(PartQuestionDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<PartQuestionDto> findById(Long questionId) {
        log.info("[PartQuestionServiceImpl][PartQuestionDto] Starts");

        if (partQuestionDao.findById(questionId).isPresent()) {
            return Optional.of(PartQuestionDto.from(partQuestionDao.findById(questionId).get()));
        } else {
        log.info("[PartQuestionServiceImpl][PartQuestionDto] empty");

            return Optional.empty();
        }

    }

    @Override
    public List<PartQuestionDto> getQuestionListbyCertificateId(Long certificateId) throws Exception {
        return partQuestionDao.findByCertificateId(certificateId).stream()
                .map(PartQuestionDto::from)
                .collect(Collectors.toList());
    };
}
