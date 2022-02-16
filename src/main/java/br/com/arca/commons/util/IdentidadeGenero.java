package br.com.arca.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdentidadeGenero {
    MULHER_CIS("MULHER_CIS"),
    HOMEM_CIS("HOMEM_CIS"),
    MULHER_TRANS("MULHER_TRANS"),
    HOMEM_TRANS("HOMEM_TRANS"),
    NAO_BINARIO("NAO_BINARIO"),
    OUTROS("OUTROS");

    private String description;

    public void redefineDescription(String strDescricao) { this.description = strDescricao; }

}
