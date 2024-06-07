package com.web.ddajait.model.dto;

import java.util.Map;

import com.web.ddajait.model.entity.UserChallengeEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserChallenge{
    @Schema(description = "나의 진행룰", example = "50")
    private int progressRate;

    @NotBlank
    @Schema(description = "챌린지 진행 단계", example = "{step : 5 , day : 2}")
    private Map<String, Object> challengeStstus;

    public static UserChallengeDto from(UserChallengeEntity entity) {
        if (entity == null)
            return null;

        return UserChallengeDto.builder()
                .progressRate(entity.getProgressRate())
                .challengeStstus(entity.getChallengeSatus())
                .challengeInfo(entity.getChallengeInfo())
                .user(entity.getUser())
                .build();
    }
}
