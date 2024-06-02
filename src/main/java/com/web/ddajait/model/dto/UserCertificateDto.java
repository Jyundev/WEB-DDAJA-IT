package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserChalllengeEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCertificateDto {
    
    
    private Long user_certificate_id;
    
    @NotBlank
    private Boolean application;

    private Boolean userResult;

    @NotBlank
    private Long certificate_id;
    @NotBlank
    private Long user_id;


        public static UserCertificateDto from(UserCertificateEntity entity) {
        if (entity == null)
            return null;

        return UserCertificateDto.builder()
                .user_certificate_id(entity.getUserCetificate_id())
                .userResult(entity.getUserResult())
                .certificate_id(entity.getCertificateInfo().getCertificate_id())
                .user_id(entity.getUser().getUser_id())
                .build();
    }
}
