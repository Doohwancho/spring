package com.kata.orderinhexagonal.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final String subject = "Authentication-Orderer";
    private final String audience = "Orderer";
    private final String issuer = "doohwancho";
    private final String claimName = "memberId";

    public String createJwtToken(long memberId) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .claim(claimName, memberId)
                .signWith(key)
                .compact();
    }

    public Long parseToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwtToken)
                .getBody().get(claimName, Long.class);
    }
}
