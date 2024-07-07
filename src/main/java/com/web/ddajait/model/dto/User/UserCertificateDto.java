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
public class UserCertificateDto {

    @Builder.Default
    private Boolean application = false;
    @Builder.Default
    private Boolean userResult = false;

    public static UserCertificateDto from(UserCertificateEntity entity) {
        if (entity == null)
            return null;

        return UserCertificateDto.builder()
                .userResult(entity.getUserResult())
                .application(entity.getApplication())
                .build();
    }

}
