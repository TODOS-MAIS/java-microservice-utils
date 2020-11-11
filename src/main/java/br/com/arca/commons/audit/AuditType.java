package br.com.arca.commons.audit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuditType {
	CRIACAO("Operação de criação no sistema"),
	ATUALIZACAO("Operação de atualização no sistema"),
	CONSULTA("Operação de consulta no sistema");

	private String descricao;
}
