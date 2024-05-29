package com.web.ddajait.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateInfoDto {

    private Long certificateId;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String certificateFullName;

    @NotBlank
    private String field;

    @NotBlank
    private String type;

    @NotBlank
    private String overview;

    private String difficulty;
    
    @NotBlank
    private List<String> eligibility;

    @NotBlank
    private List<String> examContent;

    @NotBlank
    private List<String> examStandards;

    @NotBlank
    private List<String> passCriteria;

    @NotBlank
    private String registrationLink;

    private List<String> relatedJob;

 
}
