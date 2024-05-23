package com.web.ddajait.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
// https://velog.io/@jhappy/JWT-Base64-%EC%9D%B8%EC%BD%94%EB%94%A9

@Component
public class JwtTokenProvider {
    // private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // @Value("${jwt.issuer}")
    // private String issuer;

    // private final String secretKey;
    // private Key signingKey;
    // private String tokenPrefix = "Bearer ";

    // public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
    //     this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    // }

    // @PostConstruct
    // protected void init() {
    //     byte[] keyBytes = Base64.getDecoder().decode(secretKey);
    //     this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    // }

    // public String makeAccessToken(int userUID, int userAuthUID) {
    //     Date now = new Date();

    //     return Jwts.builder()
    //             .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
    //             .setIssuer(issuer)
    //             .setIssuedAt(now)
    //             .setExpiration(new Date(now.getTime() + Duration.ofDays(30).toMillis()))
    //             .claim("userUID", userUID)
    //             .claim("userAuthUID", userAuthUID)
    //             .signWith(signingKey, SignatureAlgorithm.HS256)
    //             .compact();
    // }

    // public boolean validateToken(String token) {
    //     try {
    //         Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
    //         return true;
    //     } catch (SecurityException | MalformedJwtException e) {
    //         logger.info("Invalid JWT signature.");
    //     } catch (ExpiredJwtException e) {
    //         logger.info("Expired JWT token.");
    //     } catch (UnsupportedJwtException e) {
    //         logger.info("Unsupported JWT token.");
    //     } catch (IllegalArgumentException e) {
    //         logger.info("JWT token compact of handler are invalid.");
    //     }
    //     return false;
    // }

    // public Claims getClaimsFromToken(String token) {
    //     return Jwts.parserBuilder()
    //             .setSigningKey(signingKey)
    //             .build()
    //             .parseClaimsJws(token)
    //             .getBody();
    // }

    // public int getUserUIDFromToken(String token) {
    //     Claims claims = getClaimsFromToken(token);
    //     return claims.get("userUID", Integer.class);
    // }

    // public int getUserAuthUIDFromToken(String token) {
    //     Claims claims = getClaimsFromToken(token);
    //     return claims.get("userAuthUID", Integer.class);
    // }

    // public String getTokenPrefix() {
    //     return tokenPrefix;
    // }
}
