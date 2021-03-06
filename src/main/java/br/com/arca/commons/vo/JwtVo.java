package br.com.arca.commons.vo;

import java.util.Date;

import br.com.arca.commons.util.Profile;
import lombok.Data;

@Data
public class JwtVo {
	private Long id;
	private String subject;
	private String cpf;
	private String type;
	private Integer idBenef;
	private String phone;
	private Integer idAngel;
	private Long deliveryTokenId;
	private String nameBenef;
	private String protocol;
	private String nmeOperator;
	private Date expiration;
	private Long idCadastroBasicoBenef;
	private String newPhoneNumber;
	private String refreshToken;
	private String loginType;
	private Long idPartner;

	public Long getCadastroBasicoAffectedId() {
		if(type.equals(Profile.BENEF.name())) {
			return idCadastroBasicoBenef;
		} else if(type.equals(Profile.ATRECEPATIVO.name()) || type.equals(Profile.EXTERNAL_ACCESS.name())) {
			return idBenef != null ? idBenef.longValue() : null;
		} else if(type.equals(Profile.PARCEIRO.name())){
			return idPartner;
		}
		 else {
			return null;
		}
	}
}
