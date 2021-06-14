package br.com.arca.commons.repository.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<JsonNode, String> {
    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        return attribute != null && !attribute.toString().equals("null") ? attribute.toString() : null;
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            var mapper = new ObjectMapper();
            return dbData != null ? mapper.readTree(dbData) : null ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }
}
