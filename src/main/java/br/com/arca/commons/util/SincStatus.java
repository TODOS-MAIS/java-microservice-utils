package br.com.arca.commons.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SincStatus {
    MANUAL,
    PENDENTE,
    ERRO;

    @JsonValue
    @Override
    public String toString() {
        return name();
    }
}
