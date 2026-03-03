package com.gonzomonk.gonzoCards.service;

import com.gonzomonk.gonzoCards.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET = "635166546A576E5A7234753778214125442A472D4B6150645367566B59703373";

    public String generateToken(User user) {
        Map<String, Object> claims = Map.of("role", user.getRole().name());
        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .claims(Map.of("role", user.getRole().name()))
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser() // Теперь используется просто .parser()
                .verifyWith(getSigningKey()) // Вместо setSigningKey
                .build()
                .parseSignedClaims(token) // Вместо parseClaimsJws
                .getPayload(); // Вместо getBody
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

