package br.com.arca.commons.audit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonAuditOperation implements AuditOperation {
	CADASTRO_BASICO("Operação de cadastro básico"),
	QUESTIONARIO("Operação de questionário"),
	COLETA_BIOMETRIA("Operação de coleta de biometria"),
	COMPARACAO_BIOMETRIA("Operação de comparação de coleta de biometria"),
	VALIDACAO_ACESSO("Operação de validação de acesso"),
	LOGIN("Operação de cadastro/atualização de login"),
	AUTENTICACAO("Operação de autenticação no sistema"),
	LOGOUT("Operação de logout no sistema"),
	DELIVERY_TOKEN("Operação de geração de delivery token"),
	ENTREGA("Operação de entrega de encomenda"),
	WEBHOOK_PROTOCOLO("Operação de webhook protocol"),
	ANJO("Operação do anjo"),
	MENSAGEM("Operação de mensagem ao usuário"),
	QUALIFICADOR("Operação de qualificador de cards"),
	GRUPO("Operação de grupo"),
	;

	private String descricao;
}
