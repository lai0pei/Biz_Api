package io.lab.core.modules.login;

import io.jsonwebtoken.Jwts;
import io.lab.core.config.security.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

@Component
@Slf4j
public class OauthSrv {

    private final JwtProperties jwtProperties;

    public OauthSrv(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String keygen(){
        KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    public String createRefreshToken(UUID userId, String username) {
        Instant issuedAt = Instant.now();
        Instant expiredAt = issuedAt.plusMillis(jwtProperties.getRefreshExpirationMs());

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(username)
                .claim("userId", userId)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiredAt))
                .signWith(key())
                .compact();
    }

    public String createAccessToken(UUID userId,String username) {
        Instant issuedAt = Instant.now();
        Instant expiredAt = issuedAt.plusMillis(jwtProperties.getAccessExpirationMs());

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .id(UUID.randomUUID().toString())
                .subject(username)
                .claim("userId", userId)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiredAt))
                .signWith(key())
                .compact();
    }

    public Optional<Claims> parseClaim(String authToken) {
        try{
            Claims claims = Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload();
            return Optional.of(claims);
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return Optional.empty();
    }

//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken);
//            return true;
//        } catch (MalformedJwtException e) {
//            log.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            log.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            log.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            log.error("JWT claims string is empty: {}", e.getMessage());
//        }
//
//        return false;
//    }



    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }
}
