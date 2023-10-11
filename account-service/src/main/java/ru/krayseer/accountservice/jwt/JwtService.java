package ru.krayseer.accountservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.ApplicationConfig;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.io.Decoders.BASE64;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final ApplicationConfig applicationConfig;

    /**
     * Получить имя пользователя из токена
     * @param token токен
     * @return username пользователя
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Создать токен для пользователя
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setClaims(Collections.emptyMap())
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + applicationConfig.getTokenLifeCycle()))
                .signWith(getSignInKey(), HS256)
                .compact();
    }

    /**
     * Валидный ли токен
     */
    public Boolean isTokenValid(String token) {
        return !extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = BASE64.decode(applicationConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
