package br.com.arca.commons.util;

import br.com.arca.commons.exception.DefaultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationUtil {
    private final JwtUtil jwtUtil;

    public void validateCanGetOrUpdate(Long registrationId) {
        final var forbiddenException = new DefaultException(HttpStatus.FORBIDDEN, ConstantsMessage.FORBIDDEN_ACCESS.text());

        var jwtVo = jwtUtil.generateJwtVo().orElseThrow(forbiddenException);

        var affectedId = jwtVo.getCadastroBasicoAffectedId();

        if(!affectedId.equals(registrationId)) {
            throw forbiddenException;
        }
    }
}
