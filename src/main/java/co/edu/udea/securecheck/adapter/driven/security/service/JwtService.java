package co.edu.udea.securecheck.adapter.driven.security.service;

import co.edu.udea.securecheck.adapter.driven.security.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {
    @Value("${jwt.secret_key}")
    private String secret;

    @Value("${jwt.expiration_time}")
    private Integer expirationTime;

    public String generateToken(Map<String, String> claims, String username) {
        log.info("Generating token for user: {}", username);
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(getSignatureKey()).compact();
    }

    private Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = getClaim(token, Claims::getSubject);
        return tokenUsername.equals(username);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().verifyWith((SecretKey) getSignatureKey()).build().parseSignedClaims(token).getPayload();
        } catch (SecurityException e) {
            throw new InvalidTokenException();
        }
    }
}
