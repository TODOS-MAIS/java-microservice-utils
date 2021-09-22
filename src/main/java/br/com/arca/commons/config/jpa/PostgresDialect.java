package br.com.arca.commons.config.jpa;

import br.com.arca.commons.config.jpa.function.PostgresUnaccentFunction;
import br.com.arca.commons.config.jpa.function.PostgresStringAggFunction;
import org.hibernate.dialect.PostgreSQL9Dialect;

public class PostgresDialect extends PostgreSQL9Dialect {
    public PostgresDialect() {
        super();
        registerFunction("string_agg", new PostgresStringAggFunction());
        registerFunction("unaccent", new PostgresUnaccentFunction());
    }
}
