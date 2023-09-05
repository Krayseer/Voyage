package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.services.RedisService;
import ru.krayseer.accountservice.services.jwt.JwtService;
import java.util.concurrent.TimeUnit;

import static ru.krayseer.accountservice.services.jwt.JwtService.TOKEN_LIFE_CYCLE;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    private final JwtService jwtService;

    public String getUsernameToken(String username) {
        String token;
        if(Boolean.TRUE.equals(redisTemplate.hasKey(username))) {
            token = redisTemplate.opsForValue().get(username);
        } else {
            token = jwtService.generateToken(username);
            redisTemplate.opsForValue().set(username, token, TOKEN_LIFE_CYCLE, TimeUnit.MILLISECONDS);
        }
        return token;
    }

}
