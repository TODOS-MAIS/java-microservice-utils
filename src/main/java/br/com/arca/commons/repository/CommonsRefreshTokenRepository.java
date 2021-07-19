package br.com.arca.commons.repository;

import br.com.arca.commons.entity.RefreshTokenCommons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("refreshTokenRepositoryCommons")
public interface CommonsRefreshTokenRepository extends JpaRepository<RefreshTokenCommons, UUID> {
}
