package br.com.arca.commons.util.log;

import br.com.arca.commons.util.Constants;
import com.google.common.base.Joiner;
import org.slf4j.MDC;

import java.util.stream.Collectors;

public interface ArcaLog<E extends Enum> {
    String getShortMessage();

    String getParams();

    E getEnum();

    default String getMDCKeys() {
        String result = Constants.STRING_EMPTY.getValue();

        if (MDC.getCopyOfContextMap() != null) {
            result = Joiner.on(Constants.LOG_DELIMITER.getValue()).join(MDC.getCopyOfContextMap()
                    .entrySet()
                    .stream()
                    .filter(f -> !"name".equalsIgnoreCase(f.getKey()) && !"cid".equalsIgnoreCase(f.getKey()))
                    .map(f -> f.getKey().concat(Constants.LOG_SEPARATOR.getValue()).concat(f.getValue()
                            + Constants.STRING_EMPTY.getValue()))
                    .collect(Collectors.toList()));
        }

        return result;
    }

    default String text() {
        var name = this.getEnum().name();

        MDC.put("short_message", String.format("\"%s\"", getShortMessage().trim()));
        MDC.put("name", name);

        final String logComplementary = getMDCKeys();
        if (this.getParams() == null) {
            return String.format("cid=%s, %s, name=%s", MDC.get("cid"), logComplementary, name);
        } else {
            return String.format("cid=%s, %s, %s, name=%s", MDC.get("cid"), logComplementary, getParams(), name);
        }
    }
}
