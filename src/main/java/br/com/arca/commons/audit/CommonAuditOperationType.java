package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonAuditOperationType implements AuditOperationType {
    JOB_SINCRONIZACAO_ACAO(CommonAuditOperation.QUESTIONARIO, "Persistência de USUARIO_ACAO no banco, ao executar o job de sincronização de cadastro offline"),
    RESPOSTA_QUESTIONARIO_OFFLINE(CommonAuditOperation.QUESTIONARIO, "Cadastro de uma resposta de um questionário offline"),
    CADASTRO_QUESTIONARIO(CommonAuditOperation.QUESTIONARIO, "Cadastro de um novo questionário para um usuário"),
    RESPOSTA_QUESTIONARIO(CommonAuditOperation.QUESTIONARIO, "Resposta de questionário de usuário"),
    RECUPERACAO_QUESTIONARIO_POR_APELIDO(CommonAuditOperation.QUESTIONARIO, "Recuperação de um questionário pelo apelido"),
    RECUPERACAO_QUESTIONARIO_USUARIO(CommonAuditOperation.QUESTIONARIO, "Recuperação do questionário de um usuário"),
    RECUPERACAO_QUESTIONARIOS_DISPONIVEIS(CommonAuditOperation.QUESTIONARIO, "Recuperação de questionários disponíveis para um usuário"),
    ANJO_ENTREGA(CommonAuditOperation.ANJO, "Criação ou atualização da entrega realizada pelo anjo"),
    COMPARACAO_BIOMETRIA(CommonAuditOperation.COMPARACAO_BIOMETRIA, "Comparação da foto pela Acesso Digital"),
    AUTENTICACAO(CommonAuditOperation.AUTENTICACAO, "Login do usuário no sistema"),
    CADASTRO_MENSAGEM(CommonAuditOperation.MENSAGEM, "Cadastro de mensagem"),
    MARCAR_MENSAGEM_COMO_LIDA(CommonAuditOperation.MENSAGEM, "Leitura de mensagem de um usuário do sistema"),
    CONSULTA_MENSAGEM(CommonAuditOperation.MENSAGEM, "Consulta de mensagem"),
    CADASTRO_QUALIFICADOR(CommonAuditOperation.QUALIFICADOR, "Cadastro de qualificador de card"),
    ATUALIZACAO_QUALIFICADOR(CommonAuditOperation.QUALIFICADOR, "Atualização de qualificador de card"),
    CONSULTA_QUALIFICADOR(CommonAuditOperation.QUALIFICADOR, "Consulta de qualificador de card"),
    CONSULTA_GRUPO(CommonAuditOperation.GRUPO, "Consulta de grupo"),
    CADASTRO_GRUPO(CommonAuditOperation.GRUPO, "Cadastro de grupo"),
    ATUALIZACAO_GRUPO(CommonAuditOperation.GRUPO, "Atualização de grupo"),
    CADASTRO_CARD_GRUPO(CommonAuditOperation.GRUPO, "Cadastro de card para um grupo"),
    ATUALIZACAO_CARD_GRUPO(CommonAuditOperation.GRUPO, "Atualização de card para um grupo"),
    CADASTRO_CARD_PARCEIRO(CommonAuditOperation.GRUPO, "Cadastro de card para um parceiro"),
    ATUALIZACAO_CARD_PARCEIRO(CommonAuditOperation.GRUPO, "Atualização de card para um parceiro"),
    CADASTRO_CARD_OFERTA(CommonAuditOperation.GRUPO, "Cadastro de card para uma oferta"),
    ATUALIZACAO_CARD_OFERTA(CommonAuditOperation.GRUPO, "Atualização de card para uma oferta"),
    ;

    private CommonAuditOperation operation;
    private String detail;
}
