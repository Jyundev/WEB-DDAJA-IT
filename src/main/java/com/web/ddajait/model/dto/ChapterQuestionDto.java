package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChapterQuestionEntity;

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
public class ChapterQuestionDto {

    private Long question_id;

    @NotBlank
    private Long chapter_id;

    @NotBlank
    private String chapterName;

    @NotBlank
    private String certification_name;
    
    @NotBlank
    private String question;

    @NotBlank
    private String notes;

    @NotBlank
    private String choices;

    @NotBlank
    private int answer;

    // @URL
    private String image;

    public static ChapterQuestionDto from(ChapterQuestionEntity entity) {
        if (entity == null)
            return null;

        return ChapterQuestionDto.builder()
                .question_id(entity.getQuestion_id())
                .chapter_id(entity.getChallengeChapter().getChapter_id())
                .chapterName(entity.getChapterName())
                .certification_name(entity.getCertificateName())
                .question(entity.getQuestion())
                .notes(entity.getNotes())
                .choices(entity.getChoices())
                .answer(entity.getAnswer())
                .image(entity.getImage())
                .build();
    }
}
