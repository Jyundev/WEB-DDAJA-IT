package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.PartQuestionEntity;

public interface PartQuestionDao {

    public List<PartQuestionEntity> findByPartId(Long partId);

    public List<PartQuestionEntity> findByCetificatePartId(Long certificatePartId);

    public Optional<PartQuestionEntity> findById(Long questionId);
}
