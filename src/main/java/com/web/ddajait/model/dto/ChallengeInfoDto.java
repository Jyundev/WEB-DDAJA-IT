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
 
    @NotBlank
    private Long challengeId;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeInfo;

    @NotBlank
    private String category;

    @NotBlank
    private String book;

    private Double passRate;

    private Double memberPassRate;

    @NotBlank
    private Long certificateId;

}
