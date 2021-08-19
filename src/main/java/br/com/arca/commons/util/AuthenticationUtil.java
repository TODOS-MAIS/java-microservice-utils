package br.com.arca.commons.util;

import br.com.arca.commons.exception.DefaultException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationUtil {
    private final JwtUtil jwtUtil;

    public String getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Principal::getName).orElse(null);
    }
}
