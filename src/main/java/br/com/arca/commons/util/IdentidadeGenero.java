package br.com.arca.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdentidadeGenero {
    MULHER_CIS("Mulher cis"),
    HOMEM_CIS("Homem cis"),
    MULHER_TRANS("Mulher trans"),
    HOMEM_TRANS("Homem trans"),
    NAO_BINARIO("Não binário"),
    OUTROS("Outros");

    private String description;

    public void redefineDescription(String strDescricao) { this.description = strDescricao; }

}
