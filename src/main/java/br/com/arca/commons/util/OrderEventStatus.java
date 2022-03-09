package br.com.arca.commons.util;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderEventStatus {
    SOLICITADO,
    RECEBIDO,
    APROVADO,
    REJEITADO,
    CANCELADO,
    ENTREGUE,
    ENCERRADO,
    PROCESSANDO,
    AUSENCIA,
    PRESENCA,
    ESTORNO,
    DEVOLVIDO,
    TROCA,
    AGENDADO,
    OUTROS,
    EM_PROGRESSO,
    ;

    public static OrderEventStatus parse(String value) {
        if(StringUtils.isBlank(value)) return null;

        return OrderEventStatus.valueOf(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return name();
    }
}
