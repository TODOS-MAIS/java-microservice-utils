package br.com.arca.commons.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ResponseUtil {
    private final HttpServletResponse response;

    @Value("${refreshToken.cookie.name}")
    private String refreshTokenCookieName;

    public void setRefreshTokenCookie(UUID refreshToken, String refreshRoute, LocalDateTime expiration) {
        setCookie(refreshTokenCookieName, refreshToken.toString(), "SameSite=None", "Secure", "HttpOnly", "Path=/");
    }

    public void setCookie(String key, String value, String... options) {
        var parsedOptions = Optional.ofNullable(options).map(item -> String.join(";", item)).orElse("");
        var sb = new StringBuilder()
                .append(key)
                .append("=")
                .append(value)
                .append(";")
                .append(parsedOptions);

        response.addHeader("Set-Cookie", sb.toString());
    }
}
