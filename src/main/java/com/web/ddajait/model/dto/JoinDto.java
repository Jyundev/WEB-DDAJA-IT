package com.web.ddajait.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    @NotBlank
    String email;
    @NotBlank
    String nickname;
    @NotBlank
    String password;

}