package com.web.ddajait.model.dto;

import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;

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
                .userResult(entity.getUserResult())
                .certificate_id(entity.getCertificateInfo().getCertificate_id())
                .user_id(entity.getUser().getUser_id())
                .build();
    }
}
