package br.com.arca.commons.repository.converter;

import br.com.arca.commons.audit.AuditOperation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;
import org.postgresql.geometric.PGpoint;

@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<PGpoint, String> {


    @Override
    public String convertToDatabaseColumn(PGpoint pGpoint) {
        return pGpoint != null ? pGpoint.getValue() : null;
    }

    @Override
    public PGpoint convertToEntityAttribute(String s) {
        try {
            return s != null ? new PGpoint(s) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
