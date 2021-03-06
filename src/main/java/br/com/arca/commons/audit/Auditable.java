package br.com.arca.commons.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Auditable {
    @JsonIgnore
	String getAfetado();
    @JsonIgnore
	String getResponsavel();
    @JsonIgnore
    @Deprecated
    default AuditOperation getDefaultOperation(){ return  null; }


}
