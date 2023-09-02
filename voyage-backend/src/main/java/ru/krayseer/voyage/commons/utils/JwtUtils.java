package ru.krayseer.voyage.commons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.krayseer.voyage.ApplicationConfig;

import java.security.Key;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public final class JwtUtils {

    private final ApplicationConfig applicationConfig;

    /**
     * Получить имя пользователя из токена
     * @param token токен
     * @return username пользователя
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
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
        byte[] keyBytes = Decoders.BASE64.decode(applicationConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
