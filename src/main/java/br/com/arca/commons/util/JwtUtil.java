package br.com.arca.commons.util;

import br.com.arca.commons.util.log.CommonLogs;
import br.com.arca.commons.vo.JwtVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtUtil implements Serializable {
    private String ROLES = "ROLES";
    private String TYPE = "TYPE";

    private static final long serialVersionUID = -2550185165626007488L;
    protected static final long JWT_TOKEN_VALIDITY = 30 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;
    protected final HttpServletRequest request;

    //for retrieveing any information from token we will need the secret key
    protected final Claims getAllClaimsFromToken(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes())).parseClaimsJws(token).getBody();
    }

   public List<String> getAllRolesFromToken(String token) throws ExpiredJwtException {
        var claims = getAllClaimsFromToken(token);
        var roles = claims.get(ROLES, List.class);
        return roles;
    }

    public final boolean isTokenExpired(String token) {
        try {
            final var expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    protected String doGenerateToken(Map<String, Object> claims, String subject, String id, long validity) {
        return doGenerateToken(claims, subject, id, validity, SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()));
    }

    protected String doGenerateToken(Map<String, Object> claims, String subject, String id, long validity, SignatureAlgorithm algorithm,
                                     String secret) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000)).setId(id)
                .signWith(algorithm, secret).compact();
    }

    public Optional<String> getToken() {
        return getToken(request);
    }

    /**
     * Gets the token by the request, if it's present in header.
     * JWT Token is in the form "Bearer token". Remove Bearer word and get
     * only the Token
     * @param request Request with the header
     * @return JwtToken in the header, if present.
     */
    public Optional<String> getToken(HttpServletRequest request) {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                return Optional.of(requestTokenHeader.substring(7));
            } else {
                log.warn(String.format("JWT Token does not begin with Bearer String, token={}",requestTokenHeader));

                return Optional.empty();
            }
        } catch(IllegalStateException ex) {
            log.warn(CommonLogs.REQUEST_SCOPE_REQUIRED.text());

            return Optional.empty();
        }
    }

    public Optional<JwtVo> generateJwtVo() {
        return generateJwtVo(request);
    }

    public Optional<JwtVo> generateJwtVo(HttpServletRequest request) {
        String token = getToken(request).orElse(null);

        return generateJwtVo(token);
    }

    public Optional<JwtVo> generateJwtVo(String token) {
        if(!StringUtils.hasText(token)) {
            return Optional.empty();
        }

        var vo = new JwtVo();

        vo.setId(getId(token).orElse(null));
        vo.setSubject(getUsernameFromToken(token));
        vo.setCpf(getCpf(token).map(String::valueOf).orElse(null));
        vo.setType(getTypeToken(token).orElse(null));
        vo.setIdBenef(getIdBenf(token).orElse(null));
        vo.setIdAngel(getIdAngel(token).orElse(null));
        vo.setDeliveryTokenId(getDeliveryTokenId(token).orElse(null));
        vo.setNameBenef(getBenfName(token).orElse(null));
        vo.setProtocol(getProtocol(token).map(String::valueOf).orElse(null));
        vo.setNmeOperator(getNmeOperator(token).map(String::valueOf).orElse(null));
        vo.setExpiration(getExpirationDateFromToken(token));

        return Optional.of(vo);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final var username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Optional<String> getTypeToken(String token) {
        var type = getAllClaimsFromToken(token).get(TYPE);
        if(type == null)
            return Optional.empty();
        return Optional.of((String)type);
    }

    public Optional<Object> getProtocol(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var protocol = getAllClaimsFromToken(token).get("PROTOCOL");
            if(protocol == null) {
                return Optional.empty();
            }
            return Optional.of((String)protocol);
        }
        return Optional.empty();
    }

    public Optional<Object> getCpf(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var protocol = getAllClaimsFromToken(token).get("CPF");
            if(protocol == null) {
                return Optional.empty();
            }
            return Optional.of((String)protocol);
        }
        return Optional.empty();
    }

    public Optional<Object> getNmeOperator(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var protocol = getAllClaimsFromToken(token).get("NMEOPETATOR");
            if(protocol == null) {
                return Optional.empty();
            }
            return Optional.of((String)protocol);
        }
        return Optional.empty();
    }

    public Optional<Integer> getIdBenf(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var idBenef = getAllClaimsFromToken(token).get("IDBENEF");
            if(idBenef == null) {
                return Optional.empty();
            }
            return Optional.of(((Integer)idBenef));
        }
        return Optional.empty();
    }

    public Optional<Integer> getIdAngel(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var idAngel = getAllClaimsFromToken(token).get("ID");
            if(idAngel == null) {
                return Optional.empty();
            }
            return Optional.of(((Integer)idAngel));
        }
        return Optional.empty();
    }

    public Optional<String> getBenfName(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var idBenef = getAllClaimsFromToken(token).get("NAME_BENEF");
            if(idBenef == null) {
                return Optional.empty();
            }
            return Optional.of(((String)idBenef));
        }
        return Optional.empty();
    }

    public Optional<Long> getDeliveryTokenId(String token) {
        final var typeToken = getTypeToken(token);
        if(!typeToken.isEmpty()){
            var deliveryTokenId = getAllClaimsFromToken(token).get("DELIVERY_TOKEN_ID");
            if(deliveryTokenId != null) {
                return Optional.of(Long.parseLong(deliveryTokenId.toString()));
            }
        }
        return Optional.empty();
    }

    public Optional<Long> getId(String token) {
        final var typeToken = getTypeToken(token);

        if(!typeToken.isEmpty()){
            var id = getAllClaimsFromToken(token).getId();
            if(id != null) {
                return Optional.of(Long.parseLong(id.toString()));
            }
        }

        return Optional.empty();
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Authentication getAuthentication(String token) {
        var username = getClaimFromToken(token, Claims::getSubject);
        var roles = getAllRolesFromToken(token);
        var profileAuthorities = roles.parallelStream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, null, profileAuthorities);
    }
}

