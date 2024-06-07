package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.entity.UserWrongQuestionEntity;

import jakarta.validation.constraints.NotBlank;
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

    private Long incorrect_id;

    @NotBlank
    private Long question_id;

    @NotNull
    private UserEntity user;

    public static UserWrongQuestionDto from(UserWrongQuestionEntity entity) {
        if (entity == null)
            return null;

        return UserWrongQuestionDto.builder()
                .incorrect_id(entity.getIncorrectId())
                .question_id(entity.getPartQuestion().getQuestionId())
                .user(entity.getUser())
                .build();
    }
}
