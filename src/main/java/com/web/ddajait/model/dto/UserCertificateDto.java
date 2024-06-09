package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.UserCertificateEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCertificateDto {

    private Boolean application;

    private Boolean userResult;

    @NotNull
    private Long certificateId;

    public static UserCertificateDto from(UserCertificateEntity entity) {
        if (entity == null)
            return null;

        return UserCertificateDto.builder()
                .userResult(entity.getUserResult())
                .certificateId(entity.getCertificateInfo().getCertificateId())
                .build();
    }
}
