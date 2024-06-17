package com.web.ddajait.model.dto.ChallengePart;

import java.util.List;

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

    @NotBlank
    private Long certificateId;

    @NotBlank
    private Long certificatePartId;

    @NotBlank
    private String partName;

    @NotBlank
    private String certificationName;

    @NotBlank
    private String question;

    @NotBlank
    private String notes;

    @NotBlank
    private List<String> choices;

    @NotBlank
    private int answer;

    private String image;

    public static PartQuestionDto from(PartQuestionEntity entity) {
        if (entity == null)
            return null;

        return PartQuestionDto.builder()
                .certificateId(entity.getCertificateInfo().getCertificateId())
                .certificatePartId(entity.getCertificatePartInfo().getCertificatePartId())
                .partName(entity.getPartName())
                .certificationName(entity.getCertificateName())
                .question(entity.getQuestion())
                .notes(entity.getNotes())
                .choices(entity.getChoices())
                .answer(entity.getAnswer())
                .image(entity.getImage())
                .build();
    }
}
