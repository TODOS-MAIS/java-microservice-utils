package br.com.arca.commons.vo;

import java.util.Date;

import lombok.Data;

@Data
public class JwtVo {
	private Long id;
	private String subject;
	private String cpf;
	private String type;
	private Integer idBenef;
	private Integer idAngel;
	private Long deliveryTokenId;
	private String nameBenef;
	private String protocol;
	private String nmeOperator;
	private Date expiration;
}
