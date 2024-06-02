package com.web.ddajait.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.URL;

import com.web.ddajait.config.constant.Role;
import com.web.ddajait.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long userId;

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
    private int age;

    @Schema(description = "성별", example = "male/female")
    private String gender;

    @Builder.Default
    private Boolean isLogin = false;

    @Schema(description = "관심분야", example = "정보보안/네트워크/운영체제 등")
    private String interest;

    @Schema(description = "직업", example = "학생/직장인/취준생")
    private String job;

    @URL
    @Schema(description = "프로필이미지", example = "URL")
    private String profileImage;

    @Positive
    @Builder.Default
    private int tier = 1;


    @Schema(description = "취득자격증", example = "['정보처리기사', '리눅스마스터']")
    private String qualifiedCertificate;

    @Builder.Default
    private String role = Role.USER.name();

    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(UserEntity user) {
        if (user == null)
            return null;

        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .age(user.getAge())
                .gender(user.getGender())
                .isLogin(user.getIsLogin())
                .interest(user.getInterest())
                .job(user.getJob())
                .profileImage(user.getProfileImage())
                .tier(user.getTier())
                .qualifiedCertificate(user.getQualifiedCertificate())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    // public UserEntity toEntity() {
    // AuthorityEntity authority = AuthorityEntity.builder()
    // .authorityName("ROLE_USER")
    // .build();
    // return UserEntity.builder()
    // .email(this.email)
    // .password(this.password)
    // .nickname(this.nickname)
    // .authorities(Collections.singleton(authority))
    // .build();
    // }
}
