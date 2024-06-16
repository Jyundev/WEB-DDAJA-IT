package com.web.ddajait.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.repository.ChallengeInfoRepository;

@SpringBootTest
public class ChallengeRepositoryTest {

    @Autowired
    private ChallengeInfoRepository challengeInfoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGetAllChallenges() {

        entityManager.flush();

        // when
        List<ChallengeInfoEntity> entities = challengeInfoRepository.getAllChallenges();

        // then
        assertTrue(entities.stream().anyMatch(e -> e.getChallengeId().equals("Challenge 1")));

    }
}