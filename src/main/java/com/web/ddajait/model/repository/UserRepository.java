package com.web.ddajait.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    public UserEntity findByEmail(String email);
    
}
