package com.web.ddajait.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivateInfoDto {


    @Schema(description = "나이", example = "28")
    private int age;

    @Schema(description = "성별", example = "female")
    private String gender;

    @Schema(description = "관심분야", example = "정보보안")
    private String interest;

    @Schema(description = "프로필 이미지", example = "URL")
    private String imageUrl;

}
