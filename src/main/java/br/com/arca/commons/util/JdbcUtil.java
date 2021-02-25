package br.com.arca.commons.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JdbcUtil {
    public static LocalDateTime getSafeLocalDateTime(ResultSet rs, String column) throws SQLException {
        var date = rs.getTimestamp(column);

        if(date != null) {
            return date.toLocalDateTime();
        }

        return null;
    }

    public static LocalDate getSafeLocalDate(ResultSet rs, String column) throws SQLException {
        var date = rs.getDate(column);

        if(date != null) {
            return date.toLocalDate();
        }

        return null;
    }

    public static Long getSafeLong(ResultSet rs, String column) {
        try {
            var val = rs.getLong(column);

            return rs.wasNull() ? null : val;
        } catch (Exception ex) {
            return null;
        }
    }
}
