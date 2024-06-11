package com.web.ddajait.model.dto;

import java.util.List;

import com.web.ddajait.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserDto {

    @Email
    @NotBlank
    @Schema(description = "이메일", example = "Jyundev@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    @Schema(description = "닉네임", example = "Jyundev")
    private String nickname;

    @NotBlank
    @Schema(description = "패스워드", example = "1234qwer")
    private String password;

    @Schema(description = "나이", example = "28")
    private String age;

    @Schema(description = "성별", example = "male/female")
    private String gender;

    @Builder.Default
    private Boolean isLogin = false;

    @Schema(description = "관심분야", example = "정보보안/네트워크/운영체제 등")
    private List<String> interest;

    @Schema(description = "직업", example = "학생/직장인/취준생")
    private String job;

    // @URL
    @Schema(description = "프로필이미지", example = "URL")
    private String profileImage;

    @Schema(description = "취득자격증", example = "['정보처리기사', '리눅스마스터']")
    private List<String> qualifiedCertificate;

    public static UserDto from(UserEntity user) {
        if (user == null)
            return null;

        return UserDto.builder()

                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .age(user.getAge())
                .gender(user.getGender())
                .isLogin(user.getIsLogin())
                .interest(user.getInterest())
                .job(user.getJob())
                .profileImage(user.getProfileImage())
                .qualifiedCertificate(user.getQualifiedCertificate())
                .build();
    }

}
