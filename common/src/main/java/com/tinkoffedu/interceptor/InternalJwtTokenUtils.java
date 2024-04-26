package com.tinkoffedu.interceptor;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class InternalJwtTokenUtils {

    @Value("${security.internal.jwt.secret-key}")
    private String secretKey;
    @Value("${security.internal.jwt.expiration-time}")
    private Long expirationMinutes;

    public String createToken() {
        return Jwts.builder()
            .setExpiration(Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(expirationMinutes))))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .addClaims(Collections.singletonMap("login", "internal-client"))
            .compact();
    }

}
