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
public class UserChalllengeDto {
    
    private Long userChallengeId;

    private String progressRate;

    @NotBlank
    private String challengeStep;

    @NotBlank
    private Long challengeId;

    @NotBlank
    private Long userId;
}
