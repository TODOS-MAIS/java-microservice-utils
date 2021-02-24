package br.com.arca.commons.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
    public static Long getSafeLong(ResultSet rs, String column) throws SQLException {
        try {
            var val = rs.getLong(column);

            return rs.wasNull() ? null : val;
        } catch (Exception ex) {
            return null;
        }
    }
}
