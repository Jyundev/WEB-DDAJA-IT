package com.web.ddajait.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.UserChalllengeEntity;

public interface UserChallengeRepository extends JpaRepository<UserChalllengeEntity, Long> {

    
}