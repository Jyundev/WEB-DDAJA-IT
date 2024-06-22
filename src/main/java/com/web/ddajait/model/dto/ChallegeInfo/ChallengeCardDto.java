package com.web.ddajait.model.dto.ChallegeInfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.web.ddajait.model.dao.UserchallengeDao;
import com.web.ddajait.model.entity.ChallengeInfoEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ChallengeCardDto {

    @NotNull
    private Long challengeId;

    @NotNull
    private Long certificateId;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeDetail;

    private String passRate;

    private String memberPassRate;

    @NotBlank
    private String startDay;

    @NotBlank
    private String endDay;

    private String testDay;

    private String thumbnail;

    private String totalprogressRate;

    private int totalUser;

    public static ChallengeCardDto from(ChallengeInfoEntity entity,  UserchallengeDao userchallengeDao) {
        if (entity == null)
            return null;

        return ChallengeCardDto.builder()
                .challengeId(entity.getChallengeId())
                .challengeName(entity.getChallengeName())
                .challengeDetail(entity.getChallengeDetail())
                .startDay(timestampToString(entity.getStartDay()))
                .endDay(timestampToString(entity.getEndDay()))
                .passRate(entity.getPassRate())
                .memberPassRate(entity.getMemberPassRate())
                .totalprogressRate(entity.getTotalprogressRate())
                .totalUser(userchallengeDao.countMemberByChallengeId(entity.getChallengeId()))
                .testDay(entity.getTestDay())
                .thumbnail(entity.getThumbnail())
                .certificateId(entity.getCertificateInfo().getCertificateId())
                .build();
    }

    public static String timestampToString(Timestamp time) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(time);

    }
}
