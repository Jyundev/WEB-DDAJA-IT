package com.web.ddajait.config.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    CHALLENGER("ROLE_CHALLENGER", "챌린저");

    private final String key;
    private final String title;
}