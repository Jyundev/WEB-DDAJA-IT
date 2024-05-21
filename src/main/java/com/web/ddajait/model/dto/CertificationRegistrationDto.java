package com.web.ddajait.model.dto;

import java.sql.Timestamp;

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
public class CertificationRegistrationDto {

    @NotBlank
    private Long registrationId;
    
    @NotBlank
    private String certificateName;

    @NotBlank
    private Timestamp round;

    @NotBlank
    private Timestamp testDay;

    @NotBlank
    private Timestamp receptionStart;

    @NotBlank
    private Timestamp receptionEnd;

    @NotBlank
    private Timestamp resultDay;

    @NotBlank
    private String type;

    @NotBlank
    private Long certificateId;
    
}
