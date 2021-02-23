package br.com.arca.commons.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EthnicityEnumDeserializer.class)
public enum Ethnicity {
    B, P, A, I
}
