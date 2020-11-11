package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditOperationType {
    RECUPERAR_SENHA(AuditOperation.LOGIN, ""),
    JOB_SINCRONIZACAO_ACAO(AuditOperation.QUESTIONARIO, "Persistência de USUARIO_ACAO no banco, ao executar o job de sincronização de cadastro offline"),
    RESPOSTA_QUESTIONARIO_OFFLINE(AuditOperation.QUESTIONARIO, "Cadastro de uma resposta de um questionário offline"),
    CADASTRO_QUESTIONARIO(AuditOperation.QUESTIONARIO, "Cadastro de um novo questionário para um usuário"),
    RESPOSTA_QUESTIONARIO(AuditOperation.QUESTIONARIO, "Resposta de questionário de usuário"),
    RECUPERACAO_QUESTIONARIO_POR_APELIDO(AuditOperation.QUESTIONARIO, "Recuperação de um questionário pelo apelido"),
    RECUPERACAO_QUESTIONARIO_USUARIO(AuditOperation.QUESTIONARIO, "Recuperação do questionário de um usuário"),
    RECUPERACAO_QUESTIONARIOS_DISPONIVEIS(AuditOperation.QUESTIONARIO, "Recuperação de questionários disponíveis para um usuário"),
    ;

    private AuditOperation operation;
    private String detail;
}
