package com.web.ddajait.model.dto.CertificateInfo;

import com.web.ddajait.model.entity.CertificateInfoEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateInfoDto {

    private Long certificate_id;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String certificateFullName;

    @NotBlank
    private String field;

    @NotBlank
    private String type;

    @NotBlank
    private String overview;

    private String difficulty;

    @NotBlank
    private String eligibility;
    // private List<String> eligibility;

    @NotBlank
    private String examContent;
    // private List<String> examContent;

    @NotBlank
    private String examStandards;

    @NotBlank
    private String passCriteria;

    @NotBlank
    private String registrationLink;

    private String relatedJob;

    public static CertificateInfoDto from(CertificateInfoEntity entity) {
        if (entity == null)
            return null;

        return CertificateInfoDto.builder()
                .certificate_id(entity.getCertificateId())
                .certificateName(entity.getCertificateName())
                .certificateFullName(entity.getCertificateFullName())
                .field(entity.getField())
                .type(entity.getTypes())
                .overview(entity.getOverview())
                .difficulty(entity.getDifficulty())
                .eligibility(entity.getEligibility())
                .examContent(entity.getExamContent())
                .examStandards(entity.getExamStandards())
                .passCriteria(entity.getPassCriteria())
                .registrationLink(entity.getRegistrationLink())
                .relatedJob(entity.getRelatedJob())
                .build();

    }

}
