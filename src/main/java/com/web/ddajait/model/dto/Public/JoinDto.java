package com.web.ddajait.model.dto.Public;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record JoinDto(
    
    @Schema(description = "유저 이메일 값", example = "Jyundev@gmail.com", required = true)
    @NotBlank
    String email,
    @Schema(description = "닉네임 값", example = "Jyundev", required = true)
    @NotBlank
    String nickname,
    @Schema(description = "유저 비밀번호", example = "1234qwer", required = true)
    @NotBlank
    String password) {
}