package br.com.arca.commons.config.jpa;

import com.querydsl.core.types.Operator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostgresOps implements Operator {
    STRING_AGG(String.class),
    UNACCENT(String.class),
    ;

    private Class<?> type;
}
