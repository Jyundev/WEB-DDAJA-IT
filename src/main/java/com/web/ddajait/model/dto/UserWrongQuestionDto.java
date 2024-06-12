package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserWrongQuestionEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWrongQuestionDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long questionId;

    @NotNull
    private Long challengeId;

    public static UserWrongQuestionDto from(UserWrongQuestionEntity entity) {
        if (entity == null)
            return null;

        return UserWrongQuestionDto.builder()
                .userId(entity.getUser().getUserId())
                .challengeId(entity.getChallengeInfo().getChallengeId())
                .questionId(entity.getPartQuestion().getQuestionId())
                .build();
    }
}
