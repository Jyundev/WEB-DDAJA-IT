package com.web.ddajait.model.dto;

import java.sql.Timestamp;

import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.ChallengeInfoEntity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.text.SimpleDateFormat;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeInfoDto {

    @Hidden
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
    private String startDay;

    @NotBlank
    private String endDay;

    private String thumbnail;

    @NotNull
    private Long challengeId;

    private String totalprogressRate;

    public static ChallengeInfoDto from(ChallengeInfoEntity entity) {
        if (entity == null)
            return null;

        return ChallengeInfoDto.builder()
                .challenge_id(entity.getChallengeId())
                .challengeName(entity.getChallengeName())
                .challengeDetail(entity.getChallengeDetail())
                .startDay(timestampToString(entity.getStartDay()))
                .endDay(timestampToString(entity.getStartDay()))
                .book(entity.getBook())
                .passRate(entity.getPassRate())
                .memberPassRate(entity.getMemberPassRate())
                .challengeId(entity.getCertificateInfo().getCertificateId())
                .totalprogressRate(entity.getTotalprogressRate())
                .build();
    }

    public static String timestampToString(Timestamp time){
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(time);

    }
}
