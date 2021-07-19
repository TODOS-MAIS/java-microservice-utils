package br.com.arca.commons.util;

import br.com.arca.commons.exception.DefaultException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LoginType {
    CPF,
    TELEFONE,
    EMAIL,
    OUTRO,
    FACEBOOK;

    @JsonValue
    @Override
    public String toString() {
        return name();
    }
    
    public boolean equals(String profile) {
        return profile.equals(this.toString());
    }

    public static LoginType from(String description) {
        return Arrays.stream(LoginType.values()).filter(l -> l.name().equalsIgnoreCase(description)).findFirst()
        .orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST, "Tipo de login não encontrado"));
    }
}
