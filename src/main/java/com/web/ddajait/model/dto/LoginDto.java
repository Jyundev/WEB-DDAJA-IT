package com.web.ddajait.model.dto;

import lombok.Builder;

@Builder
public record LoginDto(
    String username,
    String password) {
}