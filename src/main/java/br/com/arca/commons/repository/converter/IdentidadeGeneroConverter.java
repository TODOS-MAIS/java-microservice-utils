package br.com.arca.commons.repository.converter;

import br.com.arca.commons.util.IdentidadeGenero;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class IdentidadeGeneroConverter implements AttributeConverter<IdentidadeGenero, String> {

    @Override
    public String convertToDatabaseColumn(IdentidadeGenero attribute) {
        return attribute.getDescription();
    }

    @Override
    public IdentidadeGenero convertToEntityAttribute(String dbData) {
        return null;
    }
}

