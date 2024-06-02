package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChallengeInfoEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeInfoDto {

    private Long challenge_id;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeDetail;

    @NotBlank
    private String book;

    private String passRate;

    private String memberPassRate;

    @NotBlank
    private Long certificate_id;

    @NotBlank
    private String totalprogressRate;

    public static ChallengeInfoDto from(ChallengeInfoEntity entity) {
        if (entity == null)
            return null;

        return ChallengeInfoDto.builder()
                .challenge_id(entity.getChallenge_id())
                .challengeName(entity.getChallengeName())
                .challengeDetail(entity.getChallengeDetail())
                .book(entity.getBook())
                .passRate(entity.getPassRate())
                .memberPassRate(entity.getMemberPassRate())
                .certificate_id(entity.getCertificateInfo().getCertificate_id())
                .totalprogressRate(entity.getTotalprogressRate())
                .build();
    }
}
