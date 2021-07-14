package br.com.arca.commons.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JdbcUtil {
    public static ZonedDateTime getSafeZonedDateTime(ResultSet rs, String column) {
        try {
            var date = rs.getTimestamp(column);

            if(date != null) {
                return date.toInstant().atZone(ZoneId.systemDefault());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static LocalDate getSafeLocalDate(ResultSet rs, String column) {
        try {
            var date = rs.getDate(column);

            if(date != null) {
                return date.toLocalDate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static Long getSafeLong(ResultSet rs, String column) {
        try {
            var val = rs.getLong(column);

            return rs.wasNull() ? null : val;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Integer getSafeInt(ResultSet rs, String column) {
        try {
            var val = rs.getInt(column);

            return rs.wasNull() ? null : val;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
