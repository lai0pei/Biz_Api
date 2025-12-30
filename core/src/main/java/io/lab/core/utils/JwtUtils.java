package io.lab.core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.lab.core.api.admin.securityDetail.AdminDetail;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.math.BigInteger;
import java.security.Key;
import java.text.DateFormat;
import java.util.Date;
import org.slf4j.Logger;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;


    @Value("${app.jwt.header}")
    private String jwtHeader;

    @Value("${app.jwt.prefix}")
    private String jwtPrefix;

    @Value("${app.jwt.expirationMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        AdminDetail userPrincipal = (AdminDetail) authentication.getPrincipal();

        return generateTokenFromUsername(userPrincipal.getUsername(), userPrincipal.getId());
    }

    public String generateTokenFromUsername(String username, long userId) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpirationMs);
        return "hello";
//        return Jwts.builder()
//                .subject(username)
//                .claim("userId", userId)
//                .issuedAt(now)
//                .expiration(exp)
//                .signWith(key())
//                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
//        SecretKey key = Jwts.SIG.HS256.key()
//        Jwts.parser().verifyWith().build()
//        return Jwts.parserBuilder().setSigningKey(key()).build()
//                .parseClaimsJws(token).getBody().getSubject();
        return "hello";
    }

    public Long getUserIdFromJwtToken(String token) {
        return 1L;
//        return Jwts.parserBuilder().setSigningKey(key()).build()
//                .parseClaimsJws(token).getBody().get("userId", Long.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
//            Jwts.parser().build().setSigningKey(key()).build().parseClaimsJws(authToken);
//            Jwts.parser().build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
