package com.web.ddajait.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeChapterDto {
    
    private Long chapterId;

    @NotBlank
    private String missionDay;

    @NotBlank
    private int chapterNum;

    @NotBlank
    private String chapterName;

    private List<String> chapterLink;

    @NotBlank
    private String chapterMission;

    @NotBlank
    private String challengeId;
    
}
