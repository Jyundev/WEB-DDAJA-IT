package com.web.ddajait.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.ddajait.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    public UserEntity findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM users WHERE email = :email", nativeQuery = true)
    int countMemberByMemberEmail(@Param("email") String email);
    
    
    @Query(value = "SELECT COUNT(*) FROM users WHERE nickname = :nickname", nativeQuery = true)
    int countMemberByMemberNickname(@Param("nickname") String nickname);

    @Query(value = "SELECT COUNT(*) FROM users WHERE nickname = :email", nativeQuery = true)
    int getUserAuthorities(@Param("nickname") String email);
    

}
