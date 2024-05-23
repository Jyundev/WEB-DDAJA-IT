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
public class ChapterQuestionDto {

    private Long questionId;

    @NotBlank
    private Long chapterId;

    @NotBlank
    private String chapter;

    @NotBlank
    private String question;

    @NotBlank
    private String notes;

    @NotBlank
    private List<String> choices;

    @NotBlank
    private int answer;

    private String image;
}
