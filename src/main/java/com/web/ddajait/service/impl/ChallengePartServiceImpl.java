package com.web.ddajait.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.ChallengePartDao;
import com.web.ddajait.model.dao.PartQuestionDao;
import com.web.ddajait.model.dao.UserchallengeDao;
import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.ChallengePart.Challenge;
import com.web.ddajait.model.dto.ChallengePart.Day;
import com.web.ddajait.model.dto.ChallengePart.Step;
import com.web.ddajait.model.dto.ChallengePart.TestQuestion;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.model.entity.PartQuestionEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.service.ChallengePartService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengePartServiceImpl implements ChallengePartService {

    private final ChallengePartDao challengePartDao;
    private final PartQuestionDao partQuestionDao;
    private final ChallengeInfoDao challengeInfoDao;
    private final UserchallengeDao userchallengeDao;

    @Override
    public List<ChallengePartDto> getAllchallengePartInfo() {
        log.info("[challengePartServiceImpl][getAllchallengePartInfo] Starts");
        return challengePartDao.getAllChallenge().stream()
                .map(ChallengePartDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public ChallengePartDto getchallengePartInfo(Long challengePartId) {
        if (challengePartDao.findChallengeById(challengePartId).isPresent()) {
            ChallengePartEntity entity = challengePartDao.findChallengeById(challengePartId).get();
            return ChallengePartDto.from(entity);

        } else {
            throw new EntityNotFoundException("Not found ChallengePartEntity");

        }

    }

    @Override
    public Challenge getChallengersDetailData(Long challengeId, Long UserId) {
        log.info("[challengePartServiceImpl][getChallengersDetailData] Starts");

        Optional<UserChallengeEntity> challengeStatus = userchallengeDao.findByUserIdChallengeId(UserId, challengeId);

        int stepv = 0;
        int dayv = 0;

        // 현재 유저의 챌린지 진행상태 가져오기
        if (challengeStatus.isPresent()) {
            UserChallengeEntity uChallengeEntity = challengeStatus.get();
            // Map<String, Object> stepStatus = uChallengeEntity.getChallengeSatus();
            // stepv = (int) stepStatus.get("step");
            // dayv = (int) stepStatus.get("day");
            stepv = uChallengeEntity.getStep();
            dayv = uChallengeEntity.getDay();

        } else {
            stepv = 0;
            dayv = 0;
        }

        final int userStep = stepv;
        final int userDay = dayv;

        Challenge challenge = new Challenge();

        if (challengeInfoDao.findById(challengeId).isPresent()) {
            ChallengeInfoEntity challengeInfoentity = challengeInfoDao.findById(challengeId).get();
            // CertificationRegistrationEntity certificationRegistrationEntity =
            // certificationRegistrationDao.findByCertificateId(challengeInfoentity.getCertificateInfo().getCertificateId()).get();

            String name = challengeInfoentity.getChallengeName();
            // String testDay = certificationRegistrationEntity.getTestDay();
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            Timestamp starTimestamp = challengeInfoentity.getStartDay();
            Timestamp endTimestamp = challengeInfoentity.getEndDay();

            long durationInMillis = endTimestamp.getTime() - starTimestamp.getTime();

            // 결과를 일 단위로 변환합니다.
            int period = (int) (durationInMillis / (1000 * 60 * 60 * 24)) + 1;

            // 전체기간 나누기 현재 유저가 진행한 데이
            int myProgress = 0;

            if (userDay != 0) {
                myProgress = period / userDay;
            }

            String startDay = outputFormat.format(starTimestamp);
            String endDay = outputFormat.format(endTimestamp);
            int totalUser = userchallengeDao.countMemberByChallengeId(challengeId);

            // 전체 유저 진행률 평균
            int totalProgress = (int) userchallengeDao.getTotalProgress(challengeId);

            challenge.setChallengeId(challengeId);
            challenge.setName(name);
            challenge.setStart(startDay);
            challenge.setEnd(endDay);
            challenge.setMy_progress(myProgress);
            challenge.setTotal_progress(totalProgress);
            challenge.setTotal_user(totalUser);
            challenge.setTest_date("-");

            List<ChallengePartEntity> partEntityList = challengePartDao.findChallengePartByChallengeId(challengeId);

            // partnum으로 그룹화하고 day로 정렬하여 맵을 생성
            Map<Integer, Map<Integer, List<ChallengePartEntity>>> groupedByPartNumAndDay = partEntityList.stream()
                    .collect(Collectors.groupingBy(
                            ChallengePartEntity::getPartNum,
                            TreeMap::new,
                            Collectors.groupingBy(
                                    ChallengePartEntity::getDay,
                                    TreeMap::new,
                                    Collectors.toList())));

            // 결과 출력
            List<Step> steps = new ArrayList<>();
            groupedByPartNumAndDay.forEach((partNum, dayMap) -> {
                // step 객체 생성
                Step step = new Step();


                step.setStep(partNum);

                // Day 리스트 생성
                List<Day> days = new ArrayList<>();
                dayMap.forEach((day, entities) -> {
                    // Day가 중복일 경우 여러 entity 생성

                    Day dayInfo = new Day();
                    Map<String, List<String>> chapterMap = new HashMap<>();

                    entities.forEach(entity -> {
                        step.setPartName(entity.getPartName());

                        dayInfo.setDay(entity.getDay());
                        dayInfo.setMemo("메모입니다");

                        if (entity.getDay() > userDay) {
                            dayInfo.setComplete(false);
                            step.setComplete(false);
                        } else {
                            dayInfo.setComplete(true);
                            step.setComplete(true);

                        }


                        // 챕터별 섹션 데이터 수집
                        if (chapterMap.containsKey(entity.getChapterName())) {
                            chapterMap.get(entity.getChapterName()).add(entity.getSectionName());
                        } else {
                            List<String> sections = new ArrayList<>();
                            sections.add(entity.getSectionName());
                            chapterMap.put(entity.getChapterName(), sections);
                        }

                        // test 데이터 생성
                        List<TestQuestion> testQuestions = new ArrayList<>();

                        if (entity.getPartName().equals("기출문제 풀이")) {

                        } else if (entity.isRandomQuestion()) {

                            Long certifocatePartId = entity.getCertificatePartInfo().getCertificatePartId();
                            List<PartQuestionEntity> partQuestionEntities = partQuestionDao
                                    .findByCetificatePartId(certifocatePartId);
                            AtomicInteger testId = new AtomicInteger(0);

                            // 랜덤으로 세가지 문제만 추출
                            Collections.shuffle(partQuestionEntities);
                            List<PartQuestionEntity> randomQuestions = partQuestionEntities.subList(0,
                                    Math.min(partQuestionEntities.size(), 3));

                            testQuestions = randomQuestions.stream()
                                    .map(testData -> {
                                        if (testData.getChoices().size() == 4) {
                                            TestQuestion testQuestion = new TestQuestion();
                                            int id = testId.incrementAndGet();
                                            testQuestion.setId(id);
                                            testQuestion.setQuestion(testData.getQuestion());
                                            testQuestion.setItem1(testData.getChoices().get(0));
                                            testQuestion.setItem2(testData.getChoices().get(1));
                                            testQuestion.setItem3(testData.getChoices().get(2));
                                            testQuestion.setItem4(testData.getChoices().get(3));
                                            testQuestion.setAnswer(testData.getAnswer());
                                            return Optional.of(testQuestion);
                                        } else {
                                            return Optional.<TestQuestion>empty();
                                        }
                                    })
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList());

                        }
                        dayInfo.setTest(testQuestions);
                    });
                    dayInfo.setChapterMap(chapterMap);
                    days.add(dayInfo);

                });
                step.setDays(days);

                steps.add(step);
            });

            challenge.setSteps(steps);
            return challenge;
        } else {
            throw new EntityNotFoundException("Not found ChallengePartEntityList");

        }
    }

}
