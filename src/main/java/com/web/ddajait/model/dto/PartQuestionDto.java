package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChallengePartEntity;
import com.web.ddajait.model.entity.PartQuestionEntity;

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
public class PartQuestionDto {

    private Long question_id;

    @NotBlank
    private ChallengePartEntity part_id;

    @NotBlank
    private String partName;

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

    public static PartQuestionDto from(PartQuestionEntity entity) {
        if (entity == null)
            return null;

        return PartQuestionDto.builder()
                .question_id(entity.getQuestion_id())
                .part_id(entity.getChallengePart())
                .partName(entity.getPartName())
                .certification_name(entity.getCertificateName())
                .question(entity.getQuestion())
                .notes(entity.getNotes())
                .choices(entity.getChoices())
                .answer(entity.getAnswer())
                .image(entity.getImage())
                .build();
    }
}
