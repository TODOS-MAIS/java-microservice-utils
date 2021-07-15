package br.com.arca.commons.config;

import br.com.arca.commons.repository.CommonsRefreshTokenRepository;
import br.com.arca.commons.util.DateUtil;
import br.com.arca.commons.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CommonsRefreshTokenRepository repository;

    private boolean isRefreshTokenValid(String refreshToken) {
        return repository.findById(UUID.fromString(refreshToken)).map(entity ->
                entity.getAtivo() && DateUtil.isDateNotExpired(LocalDateTime.now(), entity.getCriadoEm(), entity.getExpiraEm())
        ).orElse(false);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var optToken = jwtUtil.getToken();
        if (optToken.isPresent() && !jwtUtil.isTokenExpired(optToken.get())) {
            var jwt = optToken.get();

            var optRefreshToken = jwtUtil.getRefreshToken(jwt);

            if(optRefreshToken.isPresent() && isRefreshTokenValid(optRefreshToken.get()) || optRefreshToken.isEmpty()) {
                var auth = jwtUtil.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}

