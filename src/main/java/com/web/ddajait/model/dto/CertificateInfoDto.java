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
public class CertificateInfoDto {

    @NotBlank
    private Long certificateId;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String certificateFullName;

    @NotBlank
    private String field;

    @NotBlank
    private String type;

    private String relatedJob;

    private String difficulty;

    @NotBlank
    private String round;

    @NotBlank
    private String registrationFee;

    @NotBlank
    private String certificateInfo;

    @NotBlank
    private String detailInfo;

    @NotBlank
    private String examTendency;

    @NotBlank
    private String qualifications;

    @NotBlank
    private String passCriteria;
 
}
