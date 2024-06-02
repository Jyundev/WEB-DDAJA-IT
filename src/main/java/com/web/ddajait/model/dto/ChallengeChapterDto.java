package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChallengeChapterEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeChapterDto {

    private Long chapter_id;

    @NotBlank
    private int missionDay;

    @NotBlank
    private int startDay;

    @NotBlank
    private int endDay;

    @NotBlank
    private int chapterNum;

    @NotBlank
    private String chapterName;

    private String chapterLink;

    @NotBlank
    private String chapterMission;

    @NotBlank
    private Long challenge_id;

    public static ChallengeChapterDto from(ChallengeChapterEntity entity) {
        if (entity == null)
            return null;

        return ChallengeChapterDto.builder()
                .chapter_id(entity.getChapter_id())
                .missionDay(entity.getMissionDay())
                .startDay(entity.getStartDay())
                .endDay(entity.getEndDay())
                .chapterNum(entity.getChapterNum())
                .chapterName(entity.getChapterName())
                .chapterLink(entity.getChapterLink())
                .chapterMission(entity.getChapterMission())
                .challenge_id(entity.getChallengeInfo().getChallenge_id())
                .build();

    }
}
