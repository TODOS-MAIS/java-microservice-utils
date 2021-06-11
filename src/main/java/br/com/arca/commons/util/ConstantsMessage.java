package br.com.arca.commons.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConstantsMessage {
    INVALID_ENUM("Não foi possível converter o valor %s")
    ;

    private String description;

    public String text() {
        return description;
    }
}
