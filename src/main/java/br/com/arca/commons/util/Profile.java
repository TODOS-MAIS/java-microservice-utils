package br.com.arca.commons.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Profile {
    BENEF,
    ATRECEPATIVO,
    ONGANJO,
    ONGADM,
    ARCAADM,
    ARCASEG,
    EXTERNAL_ACCESS,
    PUBLIC,
    PARCEIRO,
    ANONIMO;

    @JsonValue
    @Override
    public String toString() {
        return name();
    }
    
    public boolean equals(String profile) {
        return profile.equals(this.toString());
    }
}
