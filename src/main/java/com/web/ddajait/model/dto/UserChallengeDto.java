package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserChallengeEntity;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChallengeDto {


    private String progressRate;

    @NotBlank
    private String challengeStep;

    @NotBlank
    private Long challenge_id;

    @NotBlank
    private Long user_id;

    public static UserChallengeDto from(UserChallengeEntity entity) {
        if (entity == null)
            return null;

        return UserChallengeDto.builder()
                .progressRate(entity.getProgressRate())
                .challengeStep(entity.getChallengeStep())
                .challenge_id(entity.getUserChallenge_id())
                .user_id(entity.getUser().getUser_id())
                .build();
    }
}
