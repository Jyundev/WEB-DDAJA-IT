package com.web.ddajait.model.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
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

    // 사용자 엔터티를 조회할 때 "authorities" 속성도 함께 로드
    // 주어진 사용자 이름으로 사용자 정보를 조회하고, 권한 정보도 함께 로드하여 Optional<UserEntity>로 반환
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByEmail(String email);
 

}
