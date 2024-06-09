package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.model.entity.PartQuestionEntity;

public interface PartQuestionService {

    public List<PartQuestionDto> getQuestionListByPartId(Long partId) throws Exception;

    public List<PartQuestionDto> getQuestionListbyCertificatePartID(Long certificatePartId)throws Exception;

}
