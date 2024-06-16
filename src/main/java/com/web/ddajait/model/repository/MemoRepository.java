package com.web.ddajait.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.ddajait.model.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
    @Query(value = "SELECT * FROM memo WHERE challenge_id = ?1 and user_id = ?2 and step =?3 and day = ?4", nativeQuery = true)
    Optional<MemoEntity> findMemoByChallenIdUserIdStepDay(Long challenge_id,  Long user_id, int step,  int day);
}
