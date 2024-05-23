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
public class ChallengeInfoDto {
 
    private Long challengeId;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeInfo;

    @NotBlank
    private String chapter;

    @NotBlank
    private String book;

    private String passRate;

    private String memberPassRate;

    @NotBlank
    private Long certificateId;

    @NotBlank
    private String totalprogressRate;
}
