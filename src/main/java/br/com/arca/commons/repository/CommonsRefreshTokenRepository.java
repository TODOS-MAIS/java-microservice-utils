package br.com.arca.commons.repository;

import br.com.arca.commons.entity.CommonsRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("refreshTokenRepositoryCommons")
public interface CommonsRefreshTokenRepository extends JpaRepository<CommonsRefreshToken, UUID> {
}
