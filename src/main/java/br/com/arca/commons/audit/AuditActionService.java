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

    private AuditoriaResponsavel getResponsavel(Object originalPayload, Object newPayload) {
        var optionalJwt = jwtUtil.getToken();
        if(optionalJwt.isPresent()) {
            var profile = jwtUtil.getClaimFromToken(optionalJwt.get(), claim -> claim.get("TYPE", String.class));
            if(profile.equals(Profile.ARCAADM) || profile.equals(Profile.ATRECEPATIVO)){
                String email = jwtUtil.getClaimFromToken(optionalJwt.get(), claim -> claim.get("sub", String.class));
                return new AuditoriaResponsavel(email, AuditUserIdentitier.NME_LOGIN);
            }
            if(profile.equals(Profile.BENEF)){
                String beneficiaryId = jwtUtil.getClaimFromToken(optionalJwt.get(), claim -> claim.get("ID", String.class));
                return new AuditoriaResponsavel(beneficiaryId, AuditUserIdentitier.CADASTRO_BASICO_ID);
            }
            if(profile.equals(Profile.ONGANJO)){
                String angelId = jwtUtil.getClaimFromToken(optionalJwt.get(), claim -> claim.get("ID", String.class));
                return new AuditoriaResponsavel(angelId, AuditUserIdentitier.COD_ANJO);
            }
        } else if(originalPayload instanceof Auditable) {
            return new AuditoriaResponsavel(((Auditable) originalPayload).getResponsavel(), AuditUserIdentitier.NME_LOGIN);
        } else if(newPayload instanceof Auditable) {
            return new AuditoriaResponsavel(((Auditable) newPayload).getResponsavel(), AuditUserIdentitier.NME_LOGIN);
        }

        return new AuditoriaResponsavel(authUtil.getAuthenticatedUser(), AuditUserIdentitier.NME_LOGIN);
    }

    private AuditoriaAfetado getAfetado(Object originalPayload, Object newPayload) {
        if(originalPayload instanceof Auditable) {
            return new AuditoriaAfetado(((Auditable) originalPayload).getAfetado(), AuditUserIdentitier.NME_LOGIN);
        } else if(newPayload instanceof Auditable) {
            return new AuditoriaAfetado(((Auditable) newPayload).getAfetado(), AuditUserIdentitier.NME_LOGIN);
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
        auditBase(auditAction, originalPayload, newPayload, getAfetado(originalPayload, newPayload));
    }

    public void auditWithAffectedUser(AuditAction auditAction, Object originalPayload, Object newPayload, AuditoriaAfetado affectedUser) {
        auditBase(auditAction, originalPayload, newPayload, affectedUser);
    }

    public void auditBase(AuditAction auditAction, Object originalPayload, Object newPayload, AuditoriaAfetado affectedAudit) {
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
                .auditoriaAfetado(affectedAudit)
                .auditoriaResponsavel(getResponsavel(originalPayload, newPayload))
                .tipoAlteracao(auditAction.getAuditType())
                .operacaoDe(auditAction.getOperationType().getOperation())
                .detalhe(auditAction.getOperationType().getDetail())
                .tipoOperacao(auditAction.getOperationType())
                .nivelSeveridade(auditAction.getLogLevel())
                .modulo(auditAction.getModule())
                .build();
        repository.save(audit);
    }
}
