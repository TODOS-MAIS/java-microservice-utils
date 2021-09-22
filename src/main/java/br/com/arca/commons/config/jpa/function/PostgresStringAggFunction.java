package br.com.arca.commons.config.jpa.function;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import java.util.List;

public class PostgresStringAggFunction implements SQLFunction {
    private static final int ARGUMENTS_SIZE = 2;

    @Override
    public Type getReturnType(Type columnType, Mapping mapping)
            throws QueryException {
        return new StringType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) throws QueryException {
        if (args.size() != ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    "The function must be passed 2 arguments");
        }

        String str1 = (String) args.get(0);
        String str2 = (String) args.get(1);

        return String.format(" string_agg(%s, %s) ", str1, str2);
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return false;
    }
}
