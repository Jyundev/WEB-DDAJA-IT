package com.web.ddajait.model.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.MemoDao;
import com.web.ddajait.model.entity.MemoEntity;
import com.web.ddajait.model.repository.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemoDaoImpl implements MemoDao {

    final private MemoRepository memoRepository;

    @Override
    public Optional<MemoEntity> findMemo(Long userId, Long challengeId, int step, int day) {
        return memoRepository.findMemoByChallenIdUserIdStepDay(challengeId, userId, step, day);
    }

    @Override
    public void modifyUserChallengeMemo(MemoEntity memoEntity) {
        memoRepository.save(memoEntity);
    }

    @Override
    public void saveUserChallengeMemo(MemoEntity memoEntity) {
        memoRepository.save(memoEntity);
    }

}
