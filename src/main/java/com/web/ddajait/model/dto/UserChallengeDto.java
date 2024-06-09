package com.web.ddajait.model.dto;

import java.util.Map;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChallengeDto {

    @Schema(description = "나의 진행룰", example = "50")
    private int progressRate;

    @NotBlank
    @Schema(description = "챌린지 진행 단계", example = "{step : 5 , day : 2}")
    private Map<String, Object> challengeStstus;

    @NotNull
    @Schema(description = "챌린지 id", example = "1")
    private Long challengeId;

    @NotNull
    private Long userId;

    public static UserChallengeDto from(UserChallengeEntity entity) {
        if (entity == null)
            return null;

        return UserChallengeDto.builder()
                .progressRate(entity.getProgressRate())
                .challengeStstus(entity.getChallengeSatus())
                .challengeId(entity.getChallengeInfo().getChallengeId())
                .userId(entity.getUser().getUserId())
                .build();
    }
}
