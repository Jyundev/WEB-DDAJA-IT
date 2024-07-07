package com.web.ddajait.service.impl;

import org.springframework.stereotype.Service;

import com.web.ddajait.config.error.custom.MemoNotFoundException;
import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.MemoDao;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dto.User.UserChallenge.MemoDto;
import com.web.ddajait.model.entity.MemoEntity;
import com.web.ddajait.service.MemoService;
import com.web.ddajait.util.EntityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoDao memoDao;
    private final UserDao userDao;
    private final ChallengeInfoDao challengeInfoDao;

    @Override
    public MemoDto findMemo(Long userId, Long challengeId, int step, int day) throws Exception {
        log.info("[MemoServiceImpl][findMemo] Starts");
        if (memoDao.findMemo(userId, challengeId, step, day).isPresent()) {
            MemoEntity memoEntity = memoDao.findMemo(userId, challengeId, step, day).get();

            log.info("[MemoServiceImpl][findMemo] memoEntity {}", memoEntity);

            return MemoDto.from(memoEntity);
        } else {
            throw new MemoNotFoundException("Memo not found for userId: " + userId + ", challengeId: " + challengeId + ", step: " + step + ", day: " + day);
        }
    }

    @Override
    public void saveUserChallengeMemo(MemoDto memoDto, Long userId, Long chalengeId) throws Exception {
        log.info("[MemoServiceImpl][saveUserChallengeMemo] Starts");

        MemoEntity memoEntity = new MemoEntity();

        EntityUtil.copyNonNullProperties(memoDto, memoEntity);
        memoEntity.setUser(userDao.findById(userId).get());
        memoEntity.setChallengeInfo(challengeInfoDao.findById(chalengeId).get());

        log.info("[MemoServiceImpl][saveUserChallengeMemo] memoEntity {}", memoEntity);

        memoDao.saveUserChallengeMemo(memoEntity);
    }

    @Override
    public void modifyUserChallengeMemo(Long userId, Long challengeId, MemoDto memoDto) throws Exception {
        log.info("[MemoServiceImpl][modifyUserChallengeMemo] Starts");

        MemoEntity memoEntity = new MemoEntity();
        if (memoDao.findMemo(userId, challengeId, memoDto.getStep(), memoDto.getDay()).isPresent()) {
            memoEntity = memoDao.findMemo(userId, challengeId, memoDto.getStep(), memoDto.getDay()).get();
            EntityUtil.copyNonNullProperties(memoDto, memoEntity);
            memoDao.modifyUserChallengeMemo(memoEntity);
        } else {
            EntityUtil.copyNonNullProperties(memoDto, memoEntity);
            memoEntity.setUser(userDao.findById(userId).get());
            memoEntity.setChallengeInfo(challengeInfoDao.findById(challengeId).get());
            memoDao.saveUserChallengeMemo(memoEntity);
        }
    }

}
