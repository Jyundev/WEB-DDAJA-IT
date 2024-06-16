package com.web.ddajait.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;

@SpringBootTest
public class UserChallengeRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // given
        String email = "test@gmail.com";

        // when
        UserEntity foundUser = userRepository.findByEmail(email);

        // then
        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
    }

    @Test
    public void testCountMemberByMemberEmail() {
        // given
        String email = "test@gmail.com";

        // when
        int count = userRepository.countMemberByMemberEmail(email);

        // then
        assertEquals(1, count);
    }

    @Test
    public void testCountMemberByMemberNickname() {
        // given
        String nickname = "test";

        // when
        int count = userRepository.countMemberByMemberNickname(nickname);

        // then
        assertEquals(1, count);
    }
}