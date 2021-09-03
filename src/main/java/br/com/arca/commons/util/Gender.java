package br.com.arca.commons.util;

import br.com.arca.commons.exception.DefaultException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum Gender {
    M,F,O;

    public static Gender parse(String value) {
        if(StringUtils.isBlank(value)) {
            return null;
        }

        return Arrays.stream(Gender.values()).filter(gender -> gender.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST, CommonMessages.INVALID_ENUM.text(), value));
    }
}
