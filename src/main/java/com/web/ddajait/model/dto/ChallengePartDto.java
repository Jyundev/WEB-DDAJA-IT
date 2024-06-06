package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.ChallengePartEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengePartDto {

    @NotBlank
    private Timestamp missionDay;

    @NotBlank
    private Timestamp startDay;

    @NotBlank
    private Timestamp endDay;

    @NotBlank
    private int partNum;

    @NotBlank
    private String partName;
    
    @NotBlank
    private String chapterName;
    
    @NotBlank
    private String sessionName;


    private String partLink;

    @NotBlank
    private String partMission;
    
    private String memo;

    @NotBlank
    private ChallengeInfoEntity challengeInfo;

    public static ChallengePartDto from(ChallengePartEntity entity) {
        if (entity == null)
            return null;

        return ChallengePartDto.builder()
        .challengeInfo(entity.getChallengeInfo())
        .chapterName(entity.getChapterName())
        .endDay(entity.getEndDay())
        .memo(entity.getMemo())
        .missionDay(entity.getMissionDay())
        .partLink(entity.getPartLink())
        .partMission(entity.getPartMission())
        .partName(entity.getPartName())
        .partNum(entity.getPartNum())
        .sessionName(entity.getSessionName())
        .startDay(entity.getStartDay())
        .build();
    
        
    }
}
