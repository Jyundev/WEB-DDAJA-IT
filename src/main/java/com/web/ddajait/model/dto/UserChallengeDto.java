package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserChallengeEntity;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserChallengeDto {

    @Schema(description = "나의 진행룰", example = "10", required = false)
    private int progressRate = 0;

    // @NotNull
    // @Schema(description = "챌린지 진행 단계", example = "{step : 5 , day : 2}")
    // private Map<String, Object> challengeSatus;
    @Schema(description = "진행 step", example = "2", required = false)
    private int step = 1;

    @Schema(description = "진행 day", example = "1", required = false)
    private int day = 1;

    public static UserChallengeDto from(UserChallengeEntity entity) {
        if (entity == null)
            return null;

        return UserChallengeDto.builder()
                .progressRate(entity.getProgressRate())
                .step(entity.getStep())
                .day(entity.getDay())
                .build();
    }
}
