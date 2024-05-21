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
public class UserCertificateDto {
    
    @NotBlank
    private Long userCetificateId;
    
    @NotBlank
    private Boolean application;

    @NotBlank
    private Boolean userResult;

    @NotBlank
    private Long certificateId;

    @NotBlank
    private Long userId;
}
