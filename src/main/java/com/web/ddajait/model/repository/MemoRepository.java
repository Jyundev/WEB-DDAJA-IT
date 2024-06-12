package com.web.ddajait.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.ddajait.model.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
    @Query(value = "SELECT * FROM memo WHERE challenge_id = :challenge_id and user_id = :user_id and step = :step and day = :day", nativeQuery = true)
    Optional<MemoEntity> findMemoByChallenIdUserIdStepDay(@Param("challenge_id") Long challenge_id,
            @Param("user_id") Long user_id, @Param("step") int step, @Param("day") int day);
}
