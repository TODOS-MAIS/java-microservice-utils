package br.com.arca.commons.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ResponseUtil {
    private final HttpServletResponse response;

    @Value("${refreshToken.cookie.name}")
    private String refreshTokenCookieName;

    public void setRefreshTokenCookie(UUID refreshToken, String refreshRoute, ZonedDateTime expiration) {
        setCookie(refreshTokenCookieName, refreshToken != null ? refreshToken.toString() : null,
                "Path=/" + (refreshRoute != null ? refreshRoute : ""), "SameSite=None", "Secure", "HttpOnly",
                "Expires="+ expiration.atZone(ZoneId.of("GMT")).format(DateTimeFormatter.RFC_1123_DATE_TIME));
    }

    public void removeRefreshTokenCookie(String refreshRoute) {
        setRefreshTokenCookie(null, refreshRoute, ZonedDateTime.now().minusYears(1));
    }

    public void setCookie(String key, String value, String... options) {
        var parsedOptions = Optional.ofNullable(options).map(item -> String.join("; ", item)).orElse("");
        var sb = new StringBuilder()
                .append(key)
                .append("=")
                .append(value != null ? value : "")
                .append("; ")
                .append(parsedOptions);

        response.setHeader("Set-Cookie", sb.toString());
    }
}
