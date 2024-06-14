package com.web.ddajait.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

public class ListToJsonConverter<T> implements AttributeConverter<List<T>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            System.out.println(attribute);
            throw new IllegalArgumentException("리스트를 JSON으로 변환하는 중 오류가 발생했습니다", e);
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(dbData);
            
            throw new IllegalArgumentException("JSON을 리스트로 변환하는 중 오류가 발생했습니다", e);
        }
    }
}
