package br.com.arca.commons.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AuditoriaAfetado {
    @Column(length=50)
    private String afetado;

    @Enumerated(EnumType.STRING)
    private AuditUserIdentitier tipoIdentificadorUsuarioAfetado;
}
