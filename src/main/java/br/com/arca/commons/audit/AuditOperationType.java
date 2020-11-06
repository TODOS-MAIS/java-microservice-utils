package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuditOperationType {
    RECUPERAR_SENHA(AuditOperation.LOGIN);

    private AuditOperation operacao;
}
