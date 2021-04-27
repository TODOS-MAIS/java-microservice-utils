package br.com.arca.commons.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static boolean isDateNotExpired(LocalDate dateToCompare, LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            return false;
        }
        return (!dateToCompare.isBefore(startDate) && endDate == null) || !(dateToCompare.isBefore(startDate) || dateToCompare.isAfter(endDate));
    }
}
