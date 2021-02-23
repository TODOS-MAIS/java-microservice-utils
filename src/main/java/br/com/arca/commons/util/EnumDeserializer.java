package br.com.arca.commons.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public abstract class EnumDeserializer<T extends Enum<T>> extends JsonDeserializer<T> {
    private Class<T> enumClass;

    public EnumDeserializer(final Class<T> iEnumClass) {
        super();
        enumClass = iEnumClass;
    }

    @Override
    public T deserialize(final JsonParser jp,
                         final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String value = jp.getText();
        for (final T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value)) {
                return enumValue;
            }
        }
        return null;
    }
}