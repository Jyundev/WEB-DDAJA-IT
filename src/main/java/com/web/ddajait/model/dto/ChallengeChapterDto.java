package com.web.ddajait.model.dto;

import java.security.Timestamp;

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
    
    @NotBlank
    private Long chapterId;

    @NotBlank
    private int chapterNum;

    @NotBlank
    private String chapterName;

    @NotBlank
    private Timestamp missionDay;

    @NotBlank
    private String chapterLink;

    @NotBlank
    private String chapterMission;
    
}
