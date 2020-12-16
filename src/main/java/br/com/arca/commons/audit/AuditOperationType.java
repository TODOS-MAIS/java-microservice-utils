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
    ANJO_ENTREGA(AuditOperation.ANJO, "Criação ou atualização da entrega realizada pelo anjo"),
    COMPARACAO_BIOMETRIA(AuditOperation.COMPARACAO_BIOMETRIA, "Comparação da foto pela Acesso Digital"),
    AUTENTICACAO(AuditOperation.AUTENTICACAO, "Login do usuário no sistema"),
    CADASTRO_MENSAGEM(AuditOperation.MENSAGEM, "Cadastro de mensagem"),
    MARCAR_MENSAGEM_COMO_LIDA(AuditOperation.MENSAGEM, "Leitura de mensagem de um usuário do sistema"),
    CONSULTA_MENSAGEM(AuditOperation.MENSAGEM, "Consulta de mensagem"),
    CADASTRO_QUALIFICADOR(AuditOperation.QUALIFICADOR, "Cadastro de qualificador de card"),
    ATUALIZACAO_QUALIFICADOR(AuditOperation.QUALIFICADOR, "Atualização de qualificador de card"),
    CONSULTA_QUALIFICADOR(AuditOperation.QUALIFICADOR, "Consulta de qualificador de card"),
    CONSULTA_GRUPO(AuditOperation.GRUPO, "Consulta de grupo"),
    CADASTRO_GRUPO(AuditOperation.GRUPO, "Cadastro de grupo"),
    ATUALIZACAO_GRUPO(AuditOperation.GRUPO, "Atualização de grupo"),
    CADASTRO_CARD_GRUPO(AuditOperation.GRUPO, "Cadastro de card para um grupo"),
    ATUALIZACAO_CARD_GRUPO(AuditOperation.GRUPO, "Atualização de card para um grupo"),
    CADASTRO_CARD_PARCEIRO(AuditOperation.GRUPO, "Cadastro de card para um parceiro"),
    ATUALIZACAO_CARD_PARCEIRO(AuditOperation.GRUPO, "Atualização de card para um parceiro"),
    ATUALIZACAO_CARD_GRUPO(AuditOperation.GRUPO, "Cadastro de card para um grupo"),
    CADASTRO_CARD_OFERTA(AuditOperation.GRUPO, "Cadastro de card para uma oferta"),
    ATUALIZACAO_CARD_OFERTA(AuditOperation.GRUPO, "Atualização de card para uma oferta")
    ;

    private AuditOperation operation;
    private String detail;
}
