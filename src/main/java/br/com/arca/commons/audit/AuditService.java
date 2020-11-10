package br.com.arca.commons.audit;

import br.com.arca.commons.exception.DefaultException;
import br.com.arca.commons.util.AuthenticationUtil;
import br.com.arca.commons.util.JsonUtils;
import br.com.arca.commons.util.JwtUtil;
import br.com.arca.commons.util.RequestUtil;
import br.com.arca.commons.util.log.CommonLogs;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Objects;
import java.util.function.Supplier;

import static br.com.arca.commons.util.StringUtils.*;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class AuditService {
    private final AuditRepository repository;
    private final AuthenticationUtil authUtil;
    private final JwtUtil jwtUtil;
    private final RequestUtil requestUtil;

    public <T extends Exception> Supplier<T> defaultAuditExceptionSuplier(Auditable auditable, T ex, AuditType type) {
        return () -> {
            defaultAuditException(auditable, ex, type);
            return ex;
        };
    }

    public <T extends Exception> Supplier<T> defaultAuditExceptionSuplier(Auditable auditable, T ex, AuditType type, String detalhe) {
        return () -> {
            defaultAuditException(auditable, ex, type, detalhe);
            return ex;
        };
    }

    public <T extends Exception> void defaultAuditException(Auditable auditable, T ex, AuditType type) {
        defaultAuditException(auditable, ex, type, ex.getMessage());
    }

    public <T extends Exception> void defaultAuditException(Auditable auditable, T ex, AuditType type, String detalhe) {
        defaultAudit(auditable, auditable, ex, type, detalhe);
    }

    public void defaultAudit(Auditable originalPayload, Auditable newPayload, AuditType type) {
        defaultAudit(newPayload, originalPayload, newPayload, type, null);
    }

    public void defaultAudit(Auditable originalPayload, Auditable newPayload, AuditType type, String detalhe) {
        defaultAudit(newPayload, originalPayload, newPayload, type, detalhe);
    }

    public void defaultAudit(Auditable auditable, Object originalPayload, Object newPayload, AuditType type, String detalhe) {
        var auditoria = generateDefaultAuditoriaBuilder(auditable, originalPayload, newPayload, type, detalhe);

        save(auditoria.build());
    }

    public void defaultAudit(@Valid AuditPayload audit) {
        var auditable = (Auditable) ObjectUtils.defaultIfNull(audit.getAuditable(), audit.getNewPayload());

        var builder = generateDefaultAuditoriaBuilder(auditable, audit.getOriginalPayload(),
                audit.getNewPayload(), audit.getType(), audit.getDetail());

        builder.tipoOperacao(audit.getOperationType());
        builder.codigoRetorno(String.valueOf(audit.getStatus().value()));
        builder.nivelSeveridade(audit.getLogLevel());
        builder.modulo(audit.getModule());

        save(builder.build());
    }

    public Auditoria.AuditoriaBuilder generateDefaultAuditoriaBuilder(Auditable originalPayload, Auditable newPayload, AuditType type) {
        return generateDefaultAuditoriaBuilder(newPayload, originalPayload, newPayload, type, null);
    }

    public Auditoria.AuditoriaBuilder generateDefaultAuditoriaBuilder(Auditable originalPayload, Auditable newPayload, AuditType type, String detalhe) {
        return generateDefaultAuditoriaBuilder(newPayload, originalPayload, newPayload, type, detalhe);
    }

    public Auditoria.AuditoriaBuilder generateDefaultAuditoriaBuilder(Auditable auditable, Object originalPayload, Object newPayload, AuditType type, String detalhe) {
        var builder = Auditoria.builder();

        builder.ipResponsavel(requestUtil.getIpRequest());
        builder.userAgent(requestUtil.getUserAgent());

        var jwt = jwtUtil.generateJwtVo();

        if (jwt.isPresent()) {
            builder.resposavel(jwt.get().getSubject());
        } else if (isNotBlank(auditable.getResponsavel())) {
            builder.resposavel(auditable.getResponsavel());
        } else {
            builder.resposavel(authUtil.getAuthenticatedUser());
        }

        var newPayloadAsString = getStringPayload(newPayload);
        var originalPayloadAsString = getStringPayload(originalPayload);

        if (isBlank(newPayloadAsString)) {
            builder.hashMd5(textToMd5(originalPayloadAsString));
        } else {
            builder.hashMd5(textToMd5(newPayloadAsString));
        }

        if (isNotBlank(detalhe)) {
            builder.detalhe(detalhe.substring(0, Math.min(1000, detalhe.length())));
        }

        return builder
                .payloadOriginal(originalPayloadAsString)
                .payloadNovo(newPayloadAsString)
                .operacaoDe(auditable.getDefaultOperation())
                .tipoAlteracao(type)
                .afetado(auditable.getAfetado());
    }

    public void save(Auditoria auditoria) {
        log.info(CommonLogs.AUDIT_002.text(), auditoria.getDetalhe());

        repository.save(auditoria);
    }

    private String getStringPayload(Object payload) {
        if (payload instanceof DefaultException) {
            return JsonUtils.stringify(((DefaultException) payload).getErrorResponse());
        } else if (payload instanceof Exception) {
            return JsonUtils.stringify(ExceptionUtils.getStackTrace((Exception) payload));
        } else {
            return JsonUtils.stringify(payload);
        }
    }
}
