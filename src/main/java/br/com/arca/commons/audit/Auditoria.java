package br.com.arca.commons.audit;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@ToString
@Builder
@AllArgsConstructor
@Table(name="auditoria")
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUDITORIA_CODAUDITORIA_GENERATOR", sequenceName="COD_AUDITORIA_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITORIA_CODAUDITORIA_GENERATOR")
	@Column(name="cod_auditoria", unique=true, nullable=false)
	private Long codAuditoria;

	@Column(length=50)
	private String afetado;

	@Column(name="criado_em", nullable=false)
	private LocalDateTime criadoEm;

	@Column(length=250)
	private String detalhe;

	@Column(name="hash_md5", length=250)
	private String hashMd5;
	
	@Column(name="operacao_de", length=100)
	private AuditOperation operacaoDe;

	private AuditOperationType tipoOperacao;

	@Column(name = "payload_original")
	private String payloadOriginal;

	@Column(name = "payload_novo")
	private String payloadNovo;

	@Column(length=50)
	private String resposavel;

	@Enumerated(EnumType.STRING)
	@Column(name="tipo_alteracao", length=50)
	private AuditType tipoAlteracao;
	
	@Column(name="user_agent", length=500)
	private String userAgent;
	
	@Column(name="ip_responsavel", length=50)
	private String ipResponsavel;

	@Enumerated(EnumType.STRING)
	private AuditLogLevel nivelSeveridade;

	@Enumerated(EnumType.STRING)
	private AuditModule modulo;

	@Enumerated(EnumType.STRING)
	private AuditUserIdentitier tipoIdentificadorUsuario;

	public Auditoria() {
	}
	
	@PrePersist
	public void prePersist() {
		this.criadoEm = LocalDateTime.now();
	}

	public Long getCodAuditoria() {
		return this.codAuditoria;
	}

	public void setCodAuditoria(Long codAuditoria) {
		this.codAuditoria = codAuditoria;
	}

	public String getAfetado() {
		return this.afetado;
	}

	public void setAfetado(String afetado) {
		this.afetado = afetado;
	}

	public LocalDateTime getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getHashMd5() {
		return this.hashMd5;
	}

	public void setHashMd5(String hashMd5) {
		this.hashMd5 = hashMd5;
	}

	public String getPayloadOriginal() {
		return payloadOriginal;
	}

	public void setPayloadOriginal(String payloadOriginal) {
		this.payloadOriginal = payloadOriginal;
	}

	public String getPayloadNovo() {
		return payloadNovo;
	}

	public void setPayloadNovo(String payloadNovo) {
		this.payloadNovo = payloadNovo;
	}

	public String getResposavel() {
		return this.resposavel;
	}

	public void setResposavel(String resposavel) {
		this.resposavel = resposavel;
	}
	
	public AuditOperation getOperacaoDe() {
		return operacaoDe;
	}
	
	public void setOperacaoDe(AuditOperation operacaoDe) {
		this.operacaoDe = operacaoDe;
	}
	
	public AuditType getTipoAlteracao() {
		return tipoAlteracao;
	}
	
	public void setTipoAlteracao(AuditType tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}
	
    public String getUserAgent() {
        return userAgent;
    }
    
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    public String getIpResponsavel() {
        return ipResponsavel;
    }
    
    public void setIpResponsavel(String ipResponsavel) {
        this.ipResponsavel = ipResponsavel;
    }
}