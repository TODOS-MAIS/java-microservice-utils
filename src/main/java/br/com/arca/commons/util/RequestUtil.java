package br.com.arca.commons.util;

import br.com.arca.commons.util.log.CommonLogs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class RequestUtil {
    private final String LOCAL_IP_REMOTE_ADDR = "0:0:0:0:0:0:0:1";
    private final HttpServletRequest request;

    public String getIpRequest() {
        try {
            String ip = Optional.ofNullable(request.getHeader("x-forwarded-for")).orElse(request.getRemoteAddr());
            return ip.equals(LOCAL_IP_REMOTE_ADDR) ? "127.0.0.1" : ip;
        } catch (IllegalStateException ex) {
            log.warn(CommonLogs.REQUEST_SCOPE_REQUIRED.text());
            return null;
        }
    }

    public String getUserAgent() {
        try {
            return request.getHeader("user-agent");
        } catch (IllegalStateException ex) {
            log.warn(CommonLogs.REQUEST_SCOPE_REQUIRED.text());
            return null;
        }
    }
}
