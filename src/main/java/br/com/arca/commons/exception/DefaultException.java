package br.com.arca.commons.exception;

import br.com.arca.commons.vo.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


import java.util.function.Supplier;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class DefaultException extends RuntimeException implements Supplier<DefaultException> {
    private final HttpStatus httpStatus;
    private final String message;
    private JsonNode details;

    public DefaultException(HttpStatus httpStatus, String message, Object... args) {
        this(httpStatus, String.format(message, args));
    }

    @Override
    public DefaultException get() {
        return this;
    }
    
    public ErrorResponse getErrorResponse() {
    	return ErrorResponse.builder()
                .code(String.valueOf(this.getHttpStatus().value()))
                .message(this.getMessage())
                .details(details)
                .build();
    }
}
