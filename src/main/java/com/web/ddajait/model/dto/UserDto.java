package com.web.ddajait.model.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.URL;

import com.web.ddajait.config.constant.Role;
import com.web.ddajait.model.entity.AuthorityEntity;
import com.web.ddajait.model.entity.UserEntity;

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
    private String email;

    @NotBlank
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    private String nickname;

    @NotBlank
    private String password;

    private int age;

    private String gender;

    @Builder.Default
    private Boolean isLogin = false;

    private List<String> interest;

    private String job;

    @URL
    private String profileImage;

    @Positive
    @Builder.Default
    private int tier = 1;

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
    //     AuthorityEntity authority = AuthorityEntity.builder()
    //             .authorityName("ROLE_USER")
    //             .build();
    //     return UserEntity.builder()
    //             .email(this.email)
    //             .password(this.password)
    //             .nickname(this.nickname)
    //             .authorities(Collections.singleton(authority))
    //             .build();
    // }
}
