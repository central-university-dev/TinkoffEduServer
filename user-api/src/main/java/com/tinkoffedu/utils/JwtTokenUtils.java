package com.tinkoffedu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration-time}")
    private Long expirationMinutes;

    public String createToken(String email) {
        return Jwts.builder()
            .setClaims(Jwts.claims().setSubject(email))
            .setExpiration(Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(expirationMinutes))))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        return token != null && token.startsWith(TOKEN_PREFIX) ? token.substring(TOKEN_PREFIX.length()) : null;
    }

    public Boolean isTokenExpired(Claims claims) {
        return claims == null || claims.getExpiration().before(new Date());
    }

    public Claims resolveClaims(HttpServletRequest request) {
        try {
            return parseJwtClaims(resolveToken(request));
        } catch (ExpiredJwtException e) {
            request.setAttribute("JWT is expired", e.getMessage());
            return null;
        } catch (Exception e) {
            request.setAttribute("JWT is invalid", e.getMessage());
            return null;
        }
    }

    private Claims parseJwtClaims(String token) {
        return token == null ? null : Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
