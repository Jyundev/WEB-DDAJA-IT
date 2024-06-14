package com.web.ddajait.model.dto.UserChallenge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserChallengeApiDto {

    @NotNull
    private Long challengeId;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String thumbnail;

    @NotNull
    private int progress;

}
