package com.web.ddajait.model.dto.CertificateRegister;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.web.ddajait.model.entity.CertificationRegistrationEntity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class CertificationRegistrationDto {

    @NotBlank
    @Schema(description = "자격증 정보", example = "정보보안/네트워크/운영체제 등")
    @Hidden
    private Long certificateId;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String Type;

    @NotBlank
    private String round;

    @NotBlank
    private String testDay;

    @NotBlank
    private String receptionStart;

    @NotBlank
    private String receptionEnd;

    @NotBlank
    private String resultDay;

    public static CertificationRegistrationDto from(CertificationRegistrationEntity entity) {
        if (entity == null)
            return null;

        return CertificationRegistrationDto.builder()
                .certificateId(entity.getCertificateInfo().getCertificateId())
                .certificateName(entity.getCertificateName())
                .Type(entity.getTypes())
                .round(entity.getRound())
                .testDay(entity.getTestDay())
                .receptionStart(timestampToString(entity.getReceptionStart()))
                .receptionEnd(timestampToString(entity.getReceptionEnd()))
                .resultDay(entity.getResultDay())
                .build();

    }

    public static String timestampToString(Timestamp time) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(time);

    }
}
