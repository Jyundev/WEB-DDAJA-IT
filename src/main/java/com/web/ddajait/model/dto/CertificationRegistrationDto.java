package com.web.ddajait.model.dto;

import java.sql.Timestamp;

import com.web.ddajait.model.entity.CertificationRegistrationEntity;
import com.web.ddajait.model.entity.ChallengeChapterEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificationRegistrationDto {
    private Long registration_id;
    
    @NotBlank
    private Long certificate_id;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String Type;

    @NotBlank
    private String round;

    @NotBlank
    private String testDay;

    @NotBlank
    private Timestamp receptionStart;

    @NotBlank
    private Timestamp receptionEnd;

    @NotBlank
    private String resultDay;

        public static CertificationRegistrationDto from(CertificationRegistrationEntity entity) {
        if (entity == null)
            return null;

        return CertificationRegistrationDto.builder()
                .registration_id(entity.getRegistration_id())
                .certificate_id(entity.getCertificateInfo().getCertificate_id())
                .certificateName(entity.getCertificateName())
                .Type(entity.getTypes())
                .round(entity.getRound())
                .testDay(entity.getTestDay())
                .receptionStart(entity.getReceptionStart())
                .receptionEnd(entity.getReceptionStart())
                .resultDay(entity.getResultDay())
                .build();

    }
}
