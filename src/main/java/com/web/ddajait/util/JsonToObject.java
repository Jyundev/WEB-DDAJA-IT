package com.web.ddajait.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToObject {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Object transObject(String json, Object object) {
        if (object != null) {
            try {
                return mapper.readValue(json, object.getClass());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
