package com.cybersoft.bookshop_authentication.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(Object data) {
        // Logic to generate JWT token using username
        // This is a placeholder implementation
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return io.jsonwebtoken.Jwts.builder()
                .subject("Hello")
                .signWith(key)
                .compact();
    }

    public String validateAndGetDataToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
