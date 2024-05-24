package com.web.ddajait.model.dto;

import lombok.Builder;

// https://velog.io/@whereami2048/Spring-Boot-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84-feat-JWT-Spring-Security

@Builder
public record TokenDto(
	String token) {

}