package br.com.arca.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@MappedSuperclass
@Table(name = "REFRESH_TOKEN")
public class RefreshTokenBase {
    @GeneratedValue
    @Id
    private UUID id;

    @Column(nullable = false, length = 500)
    private String userAgent;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    private ZonedDateTime criadoEm;

    @Column(nullable = false)
    private ZonedDateTime expiraEm;
}
