package com.web.ddajait.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IncorrectAnswerDto {
    
    @NotBlank
    private Long incorrectId;

    @NotBlank
    private Long examId;

    @NotBlank
    private Long userId;
}
