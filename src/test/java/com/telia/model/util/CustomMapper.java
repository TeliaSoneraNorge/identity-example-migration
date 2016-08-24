package com.telia.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomMapper<T> {

    private final Class<T> clazz;
    private final ObjectMapper objectMapper;

    public CustomMapper(Class<T> clazz) {
        this.clazz = clazz;
        objectMapper = new ObjectMapper();
    }

    public String serialize(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public T deserialize(String valueAsString) throws IOException {
        return objectMapper.readValue(valueAsString, clazz);
    }
}
