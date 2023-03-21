package com.msa.account.component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.account.vo.AccountJwtClaim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final Key secretKey;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
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
//        3600000
        return this.createToken(jwtClaim, 360000000);
    }

    public AccountJwtClaim decodeJwt(String jwtToken) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final Claims claims =  Jwts.parserBuilder()
                .setSigningKey(this.secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        return objectMapper.convertValue(claims, AccountJwtClaim.class);
    }
}
