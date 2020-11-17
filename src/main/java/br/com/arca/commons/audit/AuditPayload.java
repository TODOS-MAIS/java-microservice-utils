package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuditPayload {
    private Auditable auditable;
    @NotNull(message = "originalPayload is required")
    private Object originalPayload;
    @NotNull(message = "newPayload is required")
    private Object newPayload;
    @NotNull(message = "type is required")
    private AuditType type;
    @NotNull(message = "operationType is required")
    private AuditOperationType operationType;
    @NotNull(message = "status is required")
    private HttpStatus status;
    @NotNull(message = "logLevel is required")
    private AuditLogLevel logLevel;
    private String detail;
    @NotNull(message = "module is required")
    private AuditModule module;
}