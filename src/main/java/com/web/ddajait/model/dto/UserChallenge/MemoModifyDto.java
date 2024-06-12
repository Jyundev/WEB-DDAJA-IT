package com.web.ddajait.model.dto.UserChallenge;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemoModifyDto {

    @NotBlank
    private String memoContent;

}