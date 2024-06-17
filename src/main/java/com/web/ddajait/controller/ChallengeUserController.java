package com.web.ddajait.controller;

import com.web.ddajait.service.ChallengePartService;
import com.web.ddajait.service.MemoService;
import com.web.ddajait.service.PartQuestionService;
import com.web.ddajait.service.UserService;
import com.web.ddajait.service.UserWrongQuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ChallengeUserController {

    private final UserService userService;
    private final ChallengePartService challengePartService;
    private final MemoService memoService;
    private final PartQuestionService partQuestionService;
    private final UserWrongQuestionService userWrongQuestionService;


}
