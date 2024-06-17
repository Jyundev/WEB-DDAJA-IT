package com.web.ddajait.model.dto.Public;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginDto(
    @Schema(description = "유저 이메일 값", example = "Jyundev@gmail.com", required = true)
    @Email
    @NotBlank
    String username,
    @Schema(description = "유저 비밀번호", example = "1234qwer", required = true)
    @NotBlank
    String password) {
}