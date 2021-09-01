package br.com.arca.commons.config.jpa.function;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import java.util.List;

public class PostgresUnaccentFunction implements SQLFunction {
    private static final int ARGUMENTS_SIZE = 1;

    @Override
    public Type getReturnType(Type columnType, Mapping mapping)
            throws QueryException {
        return new StringType();
    }

    @Override
    public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) {
        if (args.size() != ARGUMENTS_SIZE) {
            throw new IllegalArgumentException("The function must be passed 1 argument");
        }

        String str1 = (String) args.get(0);

        return String.format(" unaccent(%s) ", str1);
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
