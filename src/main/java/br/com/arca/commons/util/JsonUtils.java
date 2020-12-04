package br.com.arca.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        if(objectMapper == null) {
            objectMapper = JsonMapper
                    .builder()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build()
                    .registerModule(new JavaTimeModule());
        }
        
        return objectMapper;
    }
    
    public static String stringify(Object object) {
        ObjectMapper mapper = getObjectMapper();

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static JsonNode valueToTree(Object fromValue) {
        return getObjectMapper().valueToTree(fromValue);
    }

    public static <T> T safeParse(String value, Class<T> targetClass) {
        var mapper = getObjectMapper();

        try {
            var jsonNode = mapper.readTree(value);

            return safeParse(jsonNode, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static <T> T safeParse(JsonNode value, Class<T> targetClass) {
        return getObjectMapper().convertValue(value, targetClass);
    }
}
