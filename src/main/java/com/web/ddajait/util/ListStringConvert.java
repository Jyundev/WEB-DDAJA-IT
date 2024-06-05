package com.web.ddajait.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// https://velog.io/@juice/SpringBoot-%EC%97%94%ED%8B%B0%ED%8B%B0%EC%97%90-MapStringListString%ED%83%80%EC%9E%85-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0
@Converter
public class ListStringConvert implements AttributeConverter<List<String>, String>{

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null; // 또는 적절한 기본값 반환
        }
        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(dbData.split(","));
    }
}
    

