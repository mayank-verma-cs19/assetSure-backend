package com.example.assetSure.util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing JWT (use a strong secret key, here for demo)
    private static final String SECRET_KEY = "VerySecretKeyThatShouldBeLongEnoughForHS256Algorithm!ChangeMeInProd";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate token with username claim
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("magnum")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token and get username
    public String extractUsername(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}