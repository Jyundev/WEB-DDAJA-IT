package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserWrongQuestionEntity;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private Long user_id;

    public static UserWrongQuestionDto from(UserWrongQuestionEntity entity) {
        if (entity == null)
            return null;

        return UserWrongQuestionDto.builder()
                .incorrect_id(entity.getIncorrectId())
                .question_id(entity.getPartQuestion().getQuestion_id())
                .user_id(entity.getUser().getUserId())
                .build();
    }
}
