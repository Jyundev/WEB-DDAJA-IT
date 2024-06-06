package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;

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

    private String progressRate;

    @NotBlank
    private String challengeStep;

    @NotNull
    private ChallengeInfoEntity challengeInfo;

    @NotNull
    private UserEntity user;

    public static UserChallengeDto from(UserChallengeEntity entity) {
        if (entity == null)
            return null;

        return UserChallengeDto.builder()
                .progressRate(entity.getProgressRate())
                .challengeStep(entity.getChallengeStep())
                .challengeInfo(entity.getChallengeInfo())
                .user(entity.getUser())
                .build();
    }
}
