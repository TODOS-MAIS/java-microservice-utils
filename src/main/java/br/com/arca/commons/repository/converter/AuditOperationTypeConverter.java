package br.com.arca.commons.repository.converter;

import br.com.arca.commons.audit.AuditOperation;
import br.com.arca.commons.audit.AuditOperationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AuditOperationTypeConverter implements AttributeConverter<AuditOperationType, String> {
    @Override
    public String convertToDatabaseColumn(AuditOperationType attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public AuditOperationType convertToEntityAttribute(String dbData) {
        return new AuditOperationType() {
            @Override
            public String name() {
                return dbData;
            }

            @Override
            public AuditOperation getOperation() {
                return null;
            }

            @Override
            public String getDetail() {
                return null;
            }
        };
    }
}
