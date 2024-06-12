package com.web.ddajait.model.dto.UserChallenge;

import com.web.ddajait.model.entity.MemoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class MemoDto {

    @Schema(description = "진행 step", example = "2", required = false)
    @Builder.Default
    private int step = 1;

    @Schema(description = "진행 day", example = "1", required = false)
    @Builder.Default
    private int day = 1;

    @Schema(description = "메모", example = "메모입니다", required = false)
    @Builder.Default
    private String memo = "";

    public static MemoDto from(MemoEntity entity) {
        if (entity == null)
            return null;

        return MemoDto.builder()
                .step(entity.getStep())
                .day(entity.getDay())
                .memo(entity.getMemo())
                .build();
    }
}