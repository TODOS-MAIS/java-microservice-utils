package br.com.arca.commons.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUtil {
    public String getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName() == null ? null : SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
