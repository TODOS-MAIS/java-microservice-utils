package br.com.arca.commons.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorResponse implements Serializable {
    private String code;
    private String message;
    private JsonNode details;
}
