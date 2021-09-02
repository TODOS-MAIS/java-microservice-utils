package br.com.arca.commons.config.jpa;

import com.querydsl.jpa.HQLTemplates;

public class PostgresTemplates extends HQLTemplates {
    public PostgresTemplates() {
        super();
        add(PostgresOps.STRING_AGG, "string_agg({0},{1})");
        add(PostgresOps.UNACCENT, "unaccent({0})");
    }
}
