package br.com.arca.commons.util;

public enum Constants {
    DATETIME_PATTERN ("yyyy-MM-dd HH:mm:ss"),
    DATE_PATTERN_V1 ("yyyy-MM-dd"),
    TIME_PATTERN ("HH:mm:ss"),

    STRING_EMPTY(""),
    LOG_DELIMITER(", "),
    LOG_SEPARATOR("=");
    private String value;
    Constants(String val){
        this.value = val;
    }

    public String getValue() {
        return value;
    }
}
