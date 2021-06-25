package br.com.arca.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "RefreshTokenCommons")
@Table(name = "REFRESH_TOKEN")
public class CommonsRefreshToken {
    @GeneratedValue
    @Id
    private UUID id;

    @Column(nullable = false, length = 500)
    private String userAgent;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime expiraEm;
}
