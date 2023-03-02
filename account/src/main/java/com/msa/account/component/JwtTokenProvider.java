package com.msa.account.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key secretKey;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
        System.out.println("secretKey" + secretKey);

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(IJwtClaim jwtClaim, long expiredMs) {
        return Jwts
                .builder()
                .signWith(this.secretKey)
                .setClaims(jwtClaim.toMap())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiredMs))
                .compact();
    }

    public String createToken(IJwtClaim jwtClaim) {
        return this.createToken(jwtClaim, 3600000);
    }
}
