package br.com.arca.commons.util.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum CommonLogs implements ArcaLog<CommonLogs> {
    REQUEST_SCOPE_REQUIRED("This feature requires a RequestScope, returning an empty value."),
    MD5_ERROR("MD5 error ","text={},errorMessage={}"),
    AUDIT_002("Saving audit ","detail={}"),
    AUDIT_003("Saving audit error","detail={}");

    @NonNull
    private String shortMessage;
    private String params;

    @Override
    public CommonLogs getEnum() {
        return this;
    }
}
