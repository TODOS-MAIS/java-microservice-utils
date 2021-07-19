package br.com.arca.commons.audit;

import br.com.arca.commons.exception.DefaultException;
import br.com.arca.commons.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

import static br.com.arca.commons.util.StringUtils.isBlank;
import static br.com.arca.commons.util.StringUtils.textToMd5;

@RequiredArgsConstructor
@Service
public class AuditActionService {
    private final AuditRepository repository;
    private final AuthenticationUtil authUtil;
    private final JwtUtil jwtUtil;
    private final RequestUtil requestUtil;

    private String getResponsavel(Object originalPayload, Object newPayload) {
        var optionalJwt = jwtUtil.getToken();
        if(optionalJwt.isPresent()) {
            var basicRegistrationId = jwtUtil.getClaimFromToken(optionalJwt.get(), claim -> claim.get("BASIC_REGISTRATION_ID", Integer.class));

            return basicRegistrationId != null ? basicRegistrationId.toString() : null;
        } else if(originalPayload instanceof Auditable) {
            return ((Auditable) originalPayload).getResponsavel();
        } else if(newPayload instanceof Auditable) {
            return ((Auditable) newPayload).getResponsavel();
        } else {
            return authUtil.getAuthenticatedUser();
        }
    }

    private String getAfetado(Object originalPayload, Object newPayload) {
        if(originalPayload instanceof Auditable) {
            return ((Auditable) originalPayload).getAfetado();
        } else if(newPayload instanceof Auditable) {
            return ((Auditable) newPayload).getAfetado();
        } else {
            return null;
        }
    }

    private String getStringPayload(Object payload) {
        if (payload instanceof DefaultException) {
            return JsonUtils.stringify(((DefaultException) payload).getErrorResponse());
        } else if (payload instanceof Exception) {
            return JsonUtils.stringify(ExceptionUtils.getStackTrace((Exception) payload));
        } else if(payload instanceof String) {
            return (String) payload;
        } else {
            return JsonUtils.stringify(payload);
        }
    }

    public <T extends Exception> Supplier<T> getAuditExceptionSupplier(AuditAction auditAction, Object originalPayload, T ex) {
        return () ->  auditException(auditAction, originalPayload, ex);
    }

    public <T extends Exception> T auditException(AuditAction auditAction, Object originalPayload, T ex) {
        audit(auditAction, originalPayload, ex);
        return ex;
    }

    public void audit(AuditAction auditAction, Object originalPayload, Object newPayload) {
        var builder = Auditoria.builder();
        var newPayloadAsString = getStringPayload(newPayload);
        var originalPayloadAsString = getStringPayload(originalPayload);
        if (isBlank(originalPayloadAsString)) {
            builder.hashMd5(textToMd5(newPayloadAsString));
        } else {
            builder.hashMd5(textToMd5(originalPayloadAsString));
        }
        builder.ipResponsavel(requestUtil.getIpRequest());
        builder.userAgent(requestUtil.getUserAgent());
        var audit = builder
                .payloadOriginal(originalPayloadAsString)
                .payloadNovo(newPayloadAsString)
                .afetado(getAfetado(originalPayload, newPayload))
                .resposavel(getResponsavel(originalPayload, newPayload))
                .tipoAlteracao(auditAction.getAuditType())
                .operacaoDe(auditAction.getOperationType().getOperation())
                .detalhe(auditAction.getOperationType().getDetail())
                .tipoOperacao(auditAction.getOperationType())
                .nivelSeveridade(auditAction.getLogLevel())
                .modulo(auditAction.getModule())
                .build();
        repository.save(audit);
    }

    public void auditWithUserIdentifierType(AuditAction auditAction, Object originalPayload, Object newPayload, AuditUserIdentitier auditUserIdentitier, String affectedUser, String responsibleUser) {
        var builder = Auditoria.builder();
        var newPayloadAsString = getStringPayload(newPayload);
        var originalPayloadAsString = getStringPayload(originalPayload);
        if (isBlank(originalPayloadAsString)) {
            builder.hashMd5(textToMd5(newPayloadAsString));
        } else {
            builder.hashMd5(textToMd5(originalPayloadAsString));
        }
        builder.ipResponsavel(requestUtil.getIpRequest());
        builder.userAgent(requestUtil.getUserAgent());
        var audit = builder
                .payloadOriginal(originalPayloadAsString)
                .payloadNovo(newPayloadAsString)
                .afetado(getAfetado(originalPayload, newPayload) == null ? affectedUser : getAfetado(originalPayload, newPayload))
                .resposavel(getResponsavel(originalPayload, newPayload) == null ? responsibleUser : getResponsavel(originalPayload, newPayload))
                .tipoAlteracao(auditAction.getAuditType())
                .operacaoDe(auditAction.getOperationType().getOperation())
                .detalhe(auditAction.getOperationType().getDetail())
                .tipoOperacao(auditAction.getOperationType())
                .nivelSeveridade(auditAction.getLogLevel())
                .modulo(auditAction.getModule())
                .tipoIdentificadorUsuario(auditUserIdentitier)
                .build();
        repository.save(audit);
    }
}
