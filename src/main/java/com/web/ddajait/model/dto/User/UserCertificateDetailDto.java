package com.web.ddajait.model.dto.User;

import com.web.ddajait.model.entity.UserCertificateEntity;

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
public class UserCertificateDetailDto {

    private String certificateName;

    private Long certificateId;

    private boolean isPass;

    public static UserCertificateDetailDto from(UserCertificateEntity entity) {
        if (entity == null)
            return null;

        return UserCertificateDetailDto.builder()

                .certificateName(entity.getCertificateInfo().getCertificateFullName())
                .certificateId(entity.getCertificateInfo().getCertificateId())
                .isPass(entity.getUserResult())
                .build();
    }

    @Override
    public String toString() {
        return "certificateName : " + certificateName;
    }

}
