package com.web.ddajait.model.dto;

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
public class ChapterQuestionDto {

    @NotBlank
    private Long examId;

    @NotBlank
    private Long chapterId;

    @NotBlank
    private String chapter;
    
    @NotBlank
    private String question;

    private String notes;

    @NotBlank
    private String choices;

    @NotBlank
    private int answer;

    private String image;
}
