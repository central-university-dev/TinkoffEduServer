package com.tinkoffedu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class DataEncryptUtils {

    public static String encrypt(Map<String, Object> data, String secretKey) {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .addClaims(data)
            .compact();
    }

    public static Claims decrypt(String token, String secretKey) {
        return token == null ? null : Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
