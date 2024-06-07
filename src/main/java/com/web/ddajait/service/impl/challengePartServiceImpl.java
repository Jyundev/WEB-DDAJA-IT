package com.web.ddajait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.ddajait.model.dao.ChallengePartDao;
import com.web.ddajait.model.dto.ChallengePartDto;
import com.web.ddajait.model.dto.PartQuestionDto;
import com.web.ddajait.model.dto.ChallengePart.Certificate;
import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.service.ChallengePartService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ChallengePartServiceImpl implements ChallengePartService {

    final private ChallengePartDao challengePartDao;

    @Override
    public List<ChallengePartDto> getAllchallengePartInfo() {
        log.info("[challengePartServiceImpl][getAllchallengePartInfo] Starts");
        return challengePartDao.getAllChallenge().stream()
                .map(ChallengePartDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public Certificate getChallengePart(Long challengeId) {
        // List<ChallengePartEntity> challengePartEntities =
        // 
        // challengePartDao.findChallengePartByChallengeId(challengeId);

        // log.info("[challengePartServiceImpl][getChallengePart] Starts");

        // if (!challengePartEntities.isEmpty()) {
        // 
        // info("[challengePartServiceImpl][getChallengePart] challengePartEntities
        // ePartEntities);
        // List<Step> steps = challengePartEntities.stream()
        // 
 
        //  data.getPartNum();
        // Timestamp startDay = data.getStartDay();
        //  = data.getEndDay();
 
        // long millisecondsDifference = endDay.getTime() - startDay.getTime();
        // nce = millisecondsDifference / (1000 * 60 * 60 * 24);

        // t = new ArrayList<>();
 
        //  daysDifference; i++) {
        // y(i, data.getPartMission());
        // dayList.add(day);
        // 
 
        // tep(stepNumber, dayList);
        // })
        // lect(Collectors.toList()
        // ;
 
        // String certificateName =
        // lengePartEntities.get(0).getChallengeInfo().getCertificateInfo()
        // CertificateName();
 
        // ist<String> nameList = new ArrayList<>();
        // // for (int i = 0; i < challengePartEntities.size(); i++) {
        // ameList.add(certificateName);
        // // }
 
        // 
        // ificate certificate

        // log.info("[ChallengePartServiceImpl][getChallengePart] createChalllengeData :
        // certificate);
        // return certificate;

        // } else {
        // throw new EntityNotFoundException("Not found ChallengePartEntityList");

        // }

        return null;
    }

    @Override
    public ChallengePartDto getchallengePartInfo(Long challengePartId) {
        if (challengePartDao.findChallengeById(challengePartId).isPresent()) {
            ChallengePartEntity entity = challengePartDao.findChallengeById(challengePartId).get();
            return ChallengePartDto.from(entity);

        } else {
            throw new EntityNotFoundException("Not found ChallengePartEntity");

        }


        
    @Override
    public List<PartQuestionDto> getPartQuestionDtos(Long partId) {

        return null;
    }
}
// test