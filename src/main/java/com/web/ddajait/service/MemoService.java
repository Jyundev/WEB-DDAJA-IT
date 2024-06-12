package com.web.ddajait.service;

import com.web.ddajait.model.dto.UserChallenge.MemoDto;

public interface MemoService {

    public MemoDto findMemo(Long userId, Long challengeId, int step, int day) throws Exception;

    public void saveUserChallengeMemo(MemoDto memoDto, Long userId, Long chalengeId) throws Exception;

    public void modifyUserChallengeMemo(Long userId, Long challengeId, MemoDto memoDto) throws Exception;

}
