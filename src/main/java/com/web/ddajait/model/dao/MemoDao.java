package com.web.ddajait.model.dao;

import java.util.Optional;

import com.web.ddajait.model.entity.MemoEntity;

public interface MemoDao {

    public Optional<MemoEntity> findMemo(Long userId, Long challengeId, int step, int day);

    public void saveUserChallengeMemo(MemoEntity memoEntity);

    public void modifyUserChallengeMemo(MemoEntity memoEntity);

}
