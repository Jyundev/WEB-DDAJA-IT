package com.web.ddajait.model.dao;

import java.util.List;

import com.web.ddajait.model.entity.PartQuestionEntity;

public interface PartQuestionDao {

    public List<PartQuestionEntity> findByPartId(Long partId);

    public List<PartQuestionEntity> findByCetificatePartId(Long certificatePartId);

}
