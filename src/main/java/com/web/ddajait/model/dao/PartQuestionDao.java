package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.PartQuestionEntity;

public interface PartQuestionDao {

    public List<PartQuestionEntity> findByCetificatePartId(Long certificatePartId);

    public List<PartQuestionEntity> findByCertificateId(Long certificateId);

    public Optional<PartQuestionEntity> findById(Long questionId);
}
