package com.web.ddajait.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.Converter;

// https://velog.io/@juice/SpringBoot-%EC%97%94%ED%8B%B0%ED%8B%B0%EC%97%90-MapStringListString%ED%83%80%EC%9E%85-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String toJson(T object) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonMappingException, JsonProcessingException {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> fromJsonToList(String json, TypeReference<List<T>> typeReference) throws JsonMappingException, JsonProcessingException {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
