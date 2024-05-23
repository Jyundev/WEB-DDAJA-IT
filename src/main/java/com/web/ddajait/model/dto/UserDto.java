package com.web.ddajait.model.dto;


import org.hibernate.validator.constraints.URL;

import com.web.ddajait.config.constant.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    private Boolean isLogin;

    private String interest;

    private String job;

    @URL
    private String profileImage;

    @Positive
    private int tier = 1;
    
    private String qualifiedCertificate;
    
    private String role = Role.USER.name();

}
