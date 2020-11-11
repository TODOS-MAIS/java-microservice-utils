package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuditOperationType {
    RECUPERAR_SENHA(AuditOperation.LOGIN),
    ANJO_ENTREGA(AuditOperation.ANJO),
    COMPARACAO_BIOMETRIA(AuditOperation.COMPARACAO_BIOMETRIA)
    ;

    private AuditOperation operacao;
}
