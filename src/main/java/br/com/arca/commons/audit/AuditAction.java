package br.com.arca.commons.audit;

public interface AuditAction {
    AuditType getAuditType();
    AuditOperationType getOperationType();
    AuditLogLevel getLogLevel();
    AuditModule getModule();
}
