package com.web.ddajait.service;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.dto.ChallengePart.PartQuestionDto;

public interface PartQuestionService {

    public List<PartQuestionDto> getQuestionListbyCertificatePartID(Long certificatePartId) throws Exception;
    
    public List<PartQuestionDto> getQuestionListbyCertificateId(Long certificateId) throws Exception;

    public Optional<PartQuestionDto> findById(Long questionId);

}
