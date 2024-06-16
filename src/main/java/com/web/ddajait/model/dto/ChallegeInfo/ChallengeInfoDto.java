package com.web.ddajait.model.dto.ChallegeInfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

    private Long challengeId;

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

    private String testDay;

    private String thumbnail;

    private String totalprogressRate;

    public static ChallengeInfoDto from(ChallengeInfoEntity entity) {
        if (entity == null)
            return null;

        return ChallengeInfoDto.builder()
                .challengeId(entity.getChallengeId())
                .challengeName(entity.getChallengeName())
                .challengeDetail(entity.getChallengeDetail())
                .startDay(timestampToString(entity.getStartDay()))
                .endDay(timestampToString(entity.getStartDay()))
                .book(entity.getBook())
                .passRate(entity.getPassRate())
                .memberPassRate(entity.getMemberPassRate())
                .challengeId(entity.getCertificateInfo().getCertificateId())
                .totalprogressRate(entity.getTotalprogressRate())
                .testDay(entity.getTestDay())
                .build();
    }

    public static String timestampToString(Timestamp time) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(time);

    }
}
