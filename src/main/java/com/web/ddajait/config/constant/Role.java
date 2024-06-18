package com.web.ddajait.config.constant;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    CHALLENGER("ROLE_CHALLENGER");

    private final String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}