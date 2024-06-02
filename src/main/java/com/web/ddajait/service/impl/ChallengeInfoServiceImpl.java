package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dto.ChallengeInfoDto;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.service.ChallengeInfoSercive;
import com.web.ddajait.util.EntityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ChallengeInfoServiceImpl implements ChallengeInfoSercive {
    private final ChallengeInfoDao challengeInfoDao;
    
    @Override
    public List<ChallengeInfoDto> getAllChallengeInfo() {
        return challengeInfoDao.getAllChallengeInfo().stream()
                .map(source -> {
                    ChallengeInfoDto target = new ChallengeInfoDto();
                    EntityUtil.copyNonNullProperties(source, target);
                    return target;
                })
                .collect(Collectors.toList());

    }

}
