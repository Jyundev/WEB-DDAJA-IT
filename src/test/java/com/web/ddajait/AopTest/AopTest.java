package com.web.ddajait.AopTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.web.ddajait.controller.UserApiController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
public class AopTest {

    private UserApiController userApiController;

    @Test
    public void testGetUserInfo() {
    }

}
