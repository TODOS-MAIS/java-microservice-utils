package br.com.arca.commons.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConstantsMessage {
    INVALID_ENUM("Não foi possível converter o valor %s"),
    ORDER_LIST_INVALID_FILTER("É obrigatório preencher ao menos um campo do filtro"),
    ORDER_LIST_INITIAL_DATE_GREATER_THAN_END_DATE("Data inicial deve ser maior que a data final"),
    ;

    private String description;

    public String text() {
        return description;
    }
}
