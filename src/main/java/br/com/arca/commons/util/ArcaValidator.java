package br.com.arca.commons.util;

import br.com.arca.commons.exception.DefaultException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class ArcaValidator {
    private final Validator validator;

    public <T> void validate(T bean, String prefixMessage) {
        var exceptions = validator.validate(bean);

        if (!exceptions.isEmpty()) {
            if (prefixMessage != null) {
                throw new DefaultException(HttpStatus.BAD_REQUEST, "%s: %s", prefixMessage,
                        exceptions.stream().map(ConstraintViolation::getMessage).sorted()
                                .collect(Collectors.joining(Constants.LOG_DELIMITER.getValue())));
            } else {
                throw new DefaultException(HttpStatus.BAD_REQUEST, exceptions.stream().map(ConstraintViolation::getMessage).sorted()
                        .collect(Collectors.joining(Constants.LOG_DELIMITER.getValue())));

            }
        }
    }

    public <T> void validate(T bean) {
        validate(bean, null);
    }
}
