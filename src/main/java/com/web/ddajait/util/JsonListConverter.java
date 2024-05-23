package com.web.ddajait.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

// List 타입의 데이터를 DB에 저장할 때 문자열로 직렬화
@Slf4j
@Converter
public class JsonListConverter implements AttributeConverter<List<String>, String>  {
    private static final ObjectMapper mapper = new ObjectMapper()
            // JSON 직렬화/역직렬화 시 발생할 수 있는 오류를 방지
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @Override
    // 엔티티의 속성을 JSON 문자열로 변환
    public String convertToDatabaseColumn(List<String> attribute){
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("JSON writing error", e);
            throw new IllegalArgumentException("Could not convert list to JSON string", e);
        }
    }

    @Override
    // 데이터베이스에 저장된 JSON 문자열을 엔티티의 속성으로 변환
    public List<String> convertToEntityAttribute(String dbData) {
        TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {};
        try {
            return mapper.readValue(dbData, typeReference);
        } catch (IOException e) {
            log.error("JSON reading error", e);
            throw new IllegalArgumentException("Could not convert JSON string to list", e);
        }
    }
}

