package com.web.ddajait.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.AuthorityEntity;

public interface AuthRepository extends JpaRepository<AuthorityEntity, String>{
    
}
