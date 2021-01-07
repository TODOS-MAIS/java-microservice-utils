package br.com.arca.commons.audit;

public interface AuditOperationType {
    String name();
    AuditOperation getOperation();
    String getDetail();
}
