package com.web.ddajait.model.dto;

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
    private Long certificate_id;


        public static UserCertificateDto from(UserCertificateEntity entity) {
        if (entity == null)
            return null;

        return UserCertificateDto.builder()
                .userResult(entity.getUserResult())
                .certificate_id(entity.getCertificateInfo().getCertificateId())
                .build();
    }
}
