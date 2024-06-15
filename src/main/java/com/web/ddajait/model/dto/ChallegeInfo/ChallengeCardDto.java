package com.web.ddajait.model.dto.ChallegeInfo;

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
public class ChallengeCardDto {

    @NotNull
    private Long challengeId;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeDetail;

    private String passRate;

    private String memberPassRate;

    @NotBlank
    private String startDay;

    @NotBlank
    private String endDay;

    private String thumbnail;

    private String totalprogressRate;

    private Long totalUser;
}
