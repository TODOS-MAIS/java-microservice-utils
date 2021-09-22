package br.com.arca.commons.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QueryDslUtil {
    public static BooleanExpression isDateNotExpired(LocalDate dateToCompare, DateExpression<LocalDate> startDate,
                                                     DateExpression<LocalDate> endDate) {
        if (startDate == null || dateToCompare == null) {
            return Expressions.FALSE;
        }

        var expressionDateToCompare = Expressions.asDate(dateToCompare);

        return startDate.isNotNull().and(
                startDate.loe(dateToCompare).and(endDate.isNull())
                        .or(expressionDateToCompare.between(startDate, endDate))
        );
    }

    public static BooleanExpression isDateNotExpired(LocalDateTime dateToCompare, DateExpression<LocalDateTime> startDate,
                                                     DateExpression<LocalDateTime> endDate) {
        if (startDate == null || dateToCompare == null) {
            return Expressions.FALSE;
        }

        var expressionDateToCompare = Expressions.asDate(dateToCompare);

        return startDate.isNotNull().and(
                startDate.loe(dateToCompare).and(endDate.isNull())
                        .or(expressionDateToCompare.between(startDate, endDate))
        );
    }

    public static BooleanExpression isDateExpired(LocalDate dateToCompare, DateExpression<LocalDate> startDate,
                                        DateExpression<LocalDate> endDate) {
        return isDateNotExpired(dateToCompare, startDate, endDate)
                .not();
    }

    public static BooleanExpression isDateExpired(LocalDateTime dateToCompare, DateExpression<LocalDateTime> startDate,
                                                  DateExpression<LocalDateTime> endDate) {
        return isDateNotExpired(dateToCompare, startDate, endDate)
                .not();
    }
}
