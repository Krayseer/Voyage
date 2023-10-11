package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.ApplicationConfig;
import ru.krayseer.accountservice.services.RedisService;
import ru.krayseer.accountservice.services.jwt.JwtService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final String JWT_TOKEN_PREFIX = "jwt";

    private final JwtService jwtService;

    private final ApplicationConfig applicationConfig;

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Сгенерированный jwt токен фиксируется в redis со временем хранения равным жизненному циклу токена.
     * Redis сделан для того, чтобы каждый раз не генерировать новый токен
     */
    public String getUsernameToken(String username) {
        String jwtUserRedisKey = String.format("%s:%s", JWT_TOKEN_PREFIX, username);
        String token;
        if(Boolean.TRUE.equals(redisTemplate.hasKey(jwtUserRedisKey))) {
            token = redisTemplate.opsForValue().get(jwtUserRedisKey);
        } else {
            token = jwtService.generateToken(username);
            redisTemplate.opsForValue().set(
                    jwtUserRedisKey, token, applicationConfig.getTokenLifeCycle(), TimeUnit.MILLISECONDS
            );
        }
        return token;
    }

}
