package com.web.ddajait.model.dto.User;

import java.util.List;

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
    private List<Integer> wrongQuestions;

    @NotNull
    private Long challengeId;

    @NotNull
    private int step;

    public static UserWrongQuestionDto from(UserWrongQuestionEntity entity) {
        if (entity == null)
            return null;

        return UserWrongQuestionDto.builder()
                .userId(entity.getUser().getUserId())
                .challengeId(entity.getChallengeInfo().getChallengeId())
                .wrongQuestions(entity.getWrongQuestions())
                .step(entity.getStep())
                .build();
    }
}
