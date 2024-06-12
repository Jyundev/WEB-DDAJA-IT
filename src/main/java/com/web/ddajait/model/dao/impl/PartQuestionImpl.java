package com.web.ddajait.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.PartQuestionDao;
import com.web.ddajait.model.entity.PartQuestionEntity;
import com.web.ddajait.model.repository.PartQuestionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartQuestionImpl implements PartQuestionDao {

    private final PartQuestionRepository PartQuestionRepository;

    @Override
    public Optional<PartQuestionEntity> findById(Long questionId) {
        return PartQuestionRepository.findById(questionId);
    }

    @Override
    public List<PartQuestionEntity> findByPartId(Long partId) {

        return PartQuestionRepository.findByChallengePart_ChallengePartId(partId);
    }

    @Override
    public List<PartQuestionEntity> findByCetificatePartId(Long certificatePartId) {
        log.info("[PartQuestionImpl][findByCetificatePartId] Starts");

        return PartQuestionRepository.findByCertificatePartInfo_CertificatePartId(certificatePartId);

    }

}
