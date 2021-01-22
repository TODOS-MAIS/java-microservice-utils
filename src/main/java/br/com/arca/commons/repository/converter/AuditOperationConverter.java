package br.com.arca.commons.repository.converter;

import br.com.arca.commons.audit.AuditOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AuditOperationConverter implements AttributeConverter<AuditOperation, String> {
    @Override
    public String convertToDatabaseColumn(AuditOperation attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public AuditOperation convertToEntityAttribute(String dbData) {
        return () -> dbData;
    }
}
